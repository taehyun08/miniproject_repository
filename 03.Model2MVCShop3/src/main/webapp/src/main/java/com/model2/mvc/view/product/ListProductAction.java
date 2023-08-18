package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceimpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		SearchVO searchVO=new SearchVO();

		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		
		
		
	
		searchVO.setPage(page);
		String searchKeyword = request.getParameter("searchKeyword");
		if(searchKeyword == null) {
			searchVO.setSearchKeyword("");
		}else {
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		}
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		ProductService service=new ProductServiceimpl();
		HashMap<String,Object> map=service.getProductList(searchVO);

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/product/listProductView.jsp";
	}
}
package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceimpl;


public class ListProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search searchVO=new Search();

		int currentPage=1;
		if(request.getParameter("currentPage") != null)
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		
		searchVO.setPage(currentPage);
		String orderBy = request.getParameter("order");
		String searchKeyword = request.getParameter("searchKeyword");
		String menu = request.getParameter("menu");
		if(searchKeyword == null) {
			searchVO.setSearchKeyword("");
		}else {
			searchVO.setSearchKeyword(searchKeyword);
		}
		System.out.println("searchcondition :::" +request.getParameter("searchCondition"));
		System.out.println("earchKeyword :::" +request.getParameter("searchKeyword"));
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		if(orderBy == null || orderBy.equals("")) {
			orderBy = "prodNo";
		}
		searchVO.setOrderBy(orderBy);
		
		String pageSize=getServletContext().getInitParameter("pageSize");
		String pageUnit=getServletContext().getInitParameter("pageUnit");
		searchVO.setPageUnit(Integer.parseInt(pageSize));
		
		

		System.out.println("pageSize = " + pageSize);
		System.out.println("pageUnit = " + pageUnit);
		
		ProductService service=new ProductServiceimpl();
		Map<String,Object> map=service.getProductList(searchVO);
		

		Page p = new Page(currentPage, ((Integer)map.get("count")).intValue(),Integer.parseInt(pageUnit), Integer.parseInt(pageSize));
		
		System.out.println("startpageIndex = " + p.getBeginUnitPage());
		System.out.println("endpageIndex = " + p.getEndUnitPage());
		System.out.println("maxpageIndex = " + p.getMaxPage());
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("pageVO", p);
		request.setAttribute("menu", menu);

		System.out.println("listProductAction 리턴 직전");
		System.out.println(map.get("list"));
		return "forward:/product/listProductView.jsp";
	}
}
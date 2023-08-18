package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceimpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search searchVO=new Search();
		HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("userVO")).getUserId();
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
		
		String pageSize=getServletContext().getInitParameter("pageSize");
		String pageUnit=getServletContext().getInitParameter("pageUnit");
		searchVO.setPageUnit(Integer.parseInt(pageSize));
		
		PurchaseService service=new PurchaseServiceimpl();
		HashMap<String,Object> map=service.getPurchaseList(searchVO, userId);

		
		
		Page p = new Page(page, ((Integer)map.get("count")).intValue(),Integer.parseInt(pageUnit), Integer.parseInt(pageSize));
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("pageVO", p);
		
		return "forward:/purchase/listPurchase.jsp";
	}
}
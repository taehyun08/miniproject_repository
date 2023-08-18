package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceimpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class ListPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		SearchVO searchVO=new SearchVO();
		HttpSession session = request.getSession();
		String userId = ((UserVO)session.getAttribute("userVO")).getUserId();
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		PurchaseService service=new PurchaseServiceimpl();
		HashMap<String,Object> map=service.getPurchaseList(searchVO, userId);

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/purchase/listPurchase.jsp";
	}
}
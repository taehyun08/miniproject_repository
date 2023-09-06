package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceimpl;


public class ListPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search search=new Search();
		HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("userVO")).getUserId();
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		search.setCurrentPage(page);
		
		String pageSize=getServletContext().getInitParameter("pageSize");
		String pageUnit=getServletContext().getInitParameter("pageUnit");
		search.setPageUnit(Integer.parseInt(pageSize));
		Map<String, Object> inputMap = new HashMap<>();
		inputMap.put("search", search);
		inputMap.put("userId", userId);
		
		PurchaseService service=new PurchaseServiceimpl();
		List<Purchase> list = service.getPurchaseList(inputMap);
		int count = service.getTotalCount(userId);
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("count", count);
		
		Page p = new Page(page, count, Integer.parseInt(pageUnit), Integer.parseInt(pageSize));
		
		request.setAttribute("map", resultMap);
		request.setAttribute("searchVO", search);
		request.setAttribute("pageVO", p);
		
		return "forward:/purchase/listPurchase.jsp";
	}
}
package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceimpl;

public class DeletePurchaseAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("DeletePurchaseAction Ω√¿€");
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
		
		Purchase purchaseVO = new Purchase();
		
		PurchaseService service=new PurchaseServiceimpl();
		service.deletePurchase(tranNo);
		
		request.setAttribute("purchaseVO", purchaseVO);
		System.out.println("DeletePurchaseAction ≥°");
		
		return "forward:/listPurchase.do";
	}

}

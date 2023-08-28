package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceimpl;

public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
		String tranCode = request.getParameter("tranCode");
		
		Purchase purchaseVO = new Purchase();
		purchaseVO.setTranCode(tranCode);
		purchaseVO.setTranNo(tranNo);
		
		PurchaseService service = new PurchaseServiceimpl();
		service.updateTranCode(purchaseVO);
		
		return "forward:/listPurchase.do";
	}
}
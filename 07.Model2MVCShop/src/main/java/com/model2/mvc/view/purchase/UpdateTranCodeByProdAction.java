package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceimpl;



public class UpdateTranCodeByProdAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		String tranCode = request.getParameter("tranCode");
		Product productVO = new Product();
		productVO.setProdNo(prodNo);
		
		Purchase purchaseVO = new Purchase();
		purchaseVO.setTranCode(tranCode);
		purchaseVO.setPurchaseProd(productVO);
		
		PurchaseService service = new PurchaseServiceimpl();
		service.updateTranCode(purchaseVO);
		
		return "forward:/listProduct.do?menu=manage";
	}
}
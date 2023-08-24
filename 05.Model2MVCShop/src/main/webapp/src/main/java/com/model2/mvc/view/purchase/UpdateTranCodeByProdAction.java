package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceimpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;



public class UpdateTranCodeByProdAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		String tranCode = request.getParameter("tranCode");
		ProductVO productVO = new ProductVO();
		productVO.setProdNo(prodNo);
		
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setTranCode(tranCode);
		purchaseVO.setPurchaseProd(productVO);
		
		PurchaseService service = new PurchaseServiceimpl();
		service.updateTranCode(purchaseVO);
		
		return "forward:/listProduct.do?menu=manage";
	}
}
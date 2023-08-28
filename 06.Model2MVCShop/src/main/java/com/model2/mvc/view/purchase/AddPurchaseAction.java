package com.model2.mvc.view.purchase;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceimpl;

public class AddPurchaseAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("AddPurchaseAction 클래스의 execute 실행 시작");
		Purchase purchaseVO=new Purchase();
		Product productVO = new Product();
		User userVO = new User();
		
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		userVO.setUserId(request.getParameter("buyerId"));
		
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(Date.valueOf(request.getParameter("receiverDate")));
		purchaseVO.setTranCode("2");
		
		System.out.println(purchaseVO);
		
		PurchaseService service=new PurchaseServiceimpl();
		service.addPurchase(purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		System.out.println("AddPurchaseAction 클래스의 execute 끝");
		return "forward:/purchase/addPurchase.jsp";
	}

}

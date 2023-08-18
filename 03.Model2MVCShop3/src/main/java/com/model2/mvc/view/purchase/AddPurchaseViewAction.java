package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceimpl;

public class AddPurchaseViewAction extends Action{
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("AddPurchaseViewAction 클래스의 execute 실행 시작");
		
		
		ProductService productService = new ProductServiceimpl();
		Product productVO = productService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		System.out.println(productVO);
		
		request.setAttribute("productVO", productVO);
	
		
		
		
		System.out.println("AddPurchaseViewAction 클래스의 execute 끝");
		return "forward:/purchase/addPurchaseView.jsp";
	}
}

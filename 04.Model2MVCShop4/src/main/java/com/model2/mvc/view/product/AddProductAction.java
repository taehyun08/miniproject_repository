package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceimpl;

public class AddProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("AddProductAction 클래스의 execute 실행 시작");
		Product productVO=new Product();
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setStock(Integer.parseInt(request.getParameter("stock")));
		
		System.out.println(productVO);
		
		ProductService service=new ProductServiceimpl();
		service.addProduct(productVO);
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/product/addProduct.jsp";
	}

}

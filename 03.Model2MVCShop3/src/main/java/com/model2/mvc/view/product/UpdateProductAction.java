package com.model2.mvc.view.product;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceimpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo= Integer.parseInt(request.getParameter("prodNo"));
		
		Product productVO = new Product();
		System.out.println(request.getParameter("prodName"));
		System.out.println(request.getParameter("prodDetail"));
		System.out.println(request.getParameter("manuDate"));
		System.out.println(request.getParameter("price"));
		System.out.println(request.getParameter("fileName"));
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setStock(Integer.parseInt(request.getParameter("stock")));
		
		ProductService service = new ProductServiceimpl();
		service.updateProduct(productVO);
		
		request.setAttribute("productVO", productVO);
		
		
		
		return "forward:/getProduct.do?prodNo="+prodNo;
	}
}
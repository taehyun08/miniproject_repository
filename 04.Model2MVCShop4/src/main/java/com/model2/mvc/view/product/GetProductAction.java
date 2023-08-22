package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceimpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prod_no =Integer.parseInt(request.getParameter("prodNo"));
		String menu = (String)request.getParameter("menu");
		String j = (String)request.getAttribute("jsp");
		System.out.println(j);
		
		ProductService service=new ProductServiceimpl();
		Product vo = service.getProduct(prod_no);
		
		String cookieValue = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("history")) {
					cookieValue = c.getValue();
				}
			}
		}
		cookieValue += "/" + vo.getProdNo();
		
		Cookie cookie = new Cookie("history", cookieValue);
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
		System.out.println("menu :: " + menu);
		
		request.setAttribute("menu", menu);
		request.setAttribute("productVO", vo);

		return "forward:/product/getProductView.jsp";
	}
}
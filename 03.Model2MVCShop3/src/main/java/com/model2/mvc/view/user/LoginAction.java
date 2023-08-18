package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class LoginAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		User userVO=new User();
		userVO.setUserId(request.getParameter("userId"));
		userVO.setPassword(request.getParameter("password"));
		System.out.println(userVO);
		
		UserService service=new UserServiceImpl();
		User dbVO=service.loginUser(userVO);
		System.out.println(dbVO);
		HttpSession session=request.getSession();
		session.setAttribute("userVO", dbVO);
		
		return "redirect:/index.jsp";
	}
}
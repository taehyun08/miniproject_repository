package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceimpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class UpdatePurchaseViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
		
		
		PurchaseService service=new PurchaseServiceimpl();
		
		PurchaseVO purchaseVO = service.getPurchase(tranNo);
		
		request.setAttribute("purchaseVO", purchaseVO);
		System.out.println("updatePurchaseViewActioin ³¡");
		System.out.println(purchaseVO);
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}
}

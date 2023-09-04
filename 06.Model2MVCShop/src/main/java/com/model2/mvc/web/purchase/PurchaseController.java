package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceimpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 Controller
@Controller
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public PurchaseController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addPurchaseView.do")
	public ModelAndView addPurchaseView(int prodNo) throws Exception {

		System.out.println("/addPurchaseView.do");
		Product product = productService.getProduct(prodNo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/product/addPurchaseView.jsp");
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
	@RequestMapping("/addPurchase.do")
	public ModelAndView addPurchase(Purchase purchase, User user, Product product, Model model ) throws Exception {

		System.out.println("/addPurchase.do");
		//Business Logic
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchaseService.addPurchase(purchase);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/product/addPurchase.jsp");
		modelAndView.addObject("purchase", purchase);
		
		return modelAndView;
	}
	// 여기부터 안함
	@RequestMapping("/getPurchase.do")
	public ModelAndView getPurchase( @RequestParam("prodNo") int prodNo, @ModelAttribute("menu") String menu, HttpServletRequest request, HttpServletResponse response , Model model ) throws Exception {
		
		System.out.println("/getProduct.do");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		
		String cookieValue = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("history")) {
					cookieValue = c.getValue();
				}
			}
		}
		cookieValue += "/" + product.getProdNo();
		
		Cookie cookie = new Cookie("history", cookieValue);
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
		
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
		//return "forward:/product/getProductView.jsp";
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct( @ModelAttribute("search") Search search , @RequestParam("menu") String menu, Model model ) throws Exception{
		System.out.println("/listProduct.do");
		String orderBy = search.getOrderBy();
		if(orderBy == null || orderBy.equals("")) {
			search.setOrderBy("prodNo");
		}
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		int startRowNum = search.getCurrentPage() * pageSize - pageSize+1;
		int endRowNum = startRowNum + pageSize - 1;
		System.out.println("startRowNum :: " + startRowNum + "\nendRowNum:: " + endRowNum);
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		search.setPageUnit(pageSize);
		System.out.println(search.getSearchCondition() + " " + search.getSearchKeyword());
		Map<String, Object> map = productService.getProductList(search);
		System.out.println(map);
		//Business Logic
		
		Page p = new Page(search.getCurrentPage(), ((Integer)map.get("count")).intValue(), pageUnit, pageSize);
		model.addAttribute("map", map);
		model.addAttribute("page", p);
		model.addAttribute("menu", menu);
		// Model 과 View 연결
		return "forward:/product/listProductView.jsp";
	}
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView( @RequestParam("prodNo") int prodNo , Model model) throws Exception{

		System.out.println("/updateProductView.do");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct( @ModelAttribute("product") Product product , Model model) throws Exception{

		System.out.println("/updateProduct.do");
		//Business Logic
		productService.updateProduct(product);
		
		return "forward:/getProduct.do?prodNo="+product.getProdNo();
	}
	

}
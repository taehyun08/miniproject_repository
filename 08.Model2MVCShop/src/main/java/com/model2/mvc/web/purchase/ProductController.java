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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceimpl;
import com.model2.mvc.service.user.UserService;


//==> ȸ������ Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method ���� ����
		
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml ���� �Ұ�
	//==> �Ʒ��� �ΰ��� �ּ��� Ǯ�� �ǹ̸� Ȯ�� �Ұ�
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping(value="addProduct", method=RequestMethod.GET)
	public String addProductView() throws Exception {

		System.out.println("/addProductView.do");
		
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	public String addProduct( @ModelAttribute("product") Product product ) throws Exception {

		System.out.println("/addProduct.do");
		//Business Logic
		productService.addProduct(product);
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping(value="getProduct")
	public String getProduct( @RequestParam("prodNo") int prodNo, @ModelAttribute("menu") String menu, HttpServletRequest request, HttpServletResponse response , Model model ) throws Exception {
		
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
		
		// Model �� View ����
		model.addAttribute("product", product);
		
		return "forward:/product/getProductView.jsp";
	}
	
	@RequestMapping(value="listProduct")
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
		// Model �� View ����
		return "forward:/product/listProductView.jsp";
	}
	
	@RequestMapping(value="/updateProduct", method = RequestMethod.GET)
	public String updateProductView( @RequestParam("prodNo") int prodNo , Model model) throws Exception{

		System.out.println("/updateProductView.do");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping(value="/updateProduct", method = RequestMethod.POST)
	public String updateProduct( @ModelAttribute("product") Product product , Model model) throws Exception{

		System.out.println("/updateProduct.do");
		//Business Logic
		productService.updateProduct(product);
		
		return "forward:/product/getProduct?prodNo="+product.getProdNo();
	}
	

}
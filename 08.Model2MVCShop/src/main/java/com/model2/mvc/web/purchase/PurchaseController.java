package com.model2.mvc.web.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;


//==> 회원관리 Controller
@Controller
@RequestMapping("/purchase/*")
@SessionAttributes("user")
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
	
	
	@GetMapping(value="addPurchase")
	public ModelAndView addPurchaseView(int prodNo) throws Exception {
		System.out.println("/addPurchaseView.do");
		Product product = productService.getProduct(prodNo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/purchase/addPurchaseView.jsp");
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
	@PostMapping(value="addPurchase")
	public ModelAndView addPurchase(@ModelAttribute Purchase purchase,@ModelAttribute User user,@ModelAttribute Product product, Model model ) throws Exception {

		System.out.println("/addPurchase.do");
		//Business Logic
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase = purchaseService.addPurchase(purchase);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/purchase/addPurchase.jsp");
		modelAndView.addObject("purchase", purchase);
		
		return modelAndView;
	}
	@RequestMapping(value="getPurchase")
	public ModelAndView getPurchase(int tranNo) throws Exception {
		
		System.out.println("/getProduct.do");
		//Business Logic
		ModelAndView modelAndView = new ModelAndView();
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		
		// Model 과 View 연결
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("forward:/purchase/getPurchase.jsp");
		
		
		return modelAndView;
	}
	
	@RequestMapping("listPurchase")
	public ModelAndView listPurchase( @ModelAttribute("search") Search search , @SessionAttribute("user") User user) throws Exception{
		
		System.out.println("/listPurchase.do");
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		int startRowNum = search.getCurrentPage() * pageSize - pageSize+1;
		int endRowNum = startRowNum + pageSize - 1;
		System.out.println("startRowNum :: " + startRowNum + "\nendRowNum:: " + endRowNum);
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		search.setPageUnit(pageSize);
		Map<String, Object> inputMap = new HashMap<>();
		inputMap.put("userId", user.getUserId());
		inputMap.put("search", search);
		
		
		List<Purchase> list = purchaseService.getPurchaseList(inputMap);
		int count = purchaseService.getTotalCount(user.getUserId());
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("count", count);
		//Business Logic
		ModelAndView modelAndView = new ModelAndView();
		
		Page p = new Page(search.getCurrentPage(), ((Integer)resultMap.get("count")).intValue(), pageUnit, pageSize);
		modelAndView.addObject("map", resultMap);
		modelAndView.addObject("page", p);
		modelAndView.setViewName("forward:/purchase/listPurchase.jsp");
		// Model 과 View 연결
		return modelAndView;
	}
	
	@GetMapping(value="updatePurchaseView")
	public ModelAndView updatePurchaseView( @RequestParam("tranNo") int tranNo) throws Exception{

		System.out.println("/updatePurchaseView.do");
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("forward:/purchase/updatePurchaseView.jsp");
		
		return modelAndView;
	}
	
	@PostMapping(value="updatePurchase")
	public ModelAndView updatePurchase( @ModelAttribute("purchase") Purchase purchase) throws Exception{

		System.out.println("/updatePurchase.do");
		//Business Logic
		Purchase returnPurchase = purchaseService.updatePurchase(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", returnPurchase);
		modelAndView.setViewName("forward:/purchase/getPurchase?tranNo=" + purchase.getTranNo());
		
		return modelAndView;
	}
	
	@RequestMapping(value="updateTranCodeByProd")
	public ModelAndView updateTranCodeByProd( @ModelAttribute Product product, @RequestParam String tranCode) throws Exception{

		System.out.println("/updateTranCodeByProd.do");
		//Business Logic
		Purchase purchase = new Purchase();
		purchase.setPurchaseProd(product);
		purchase.setTranCode(tranCode);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("forward:/product/listProduct?menu=manage");
		
		return modelAndView;
	}
	
	@RequestMapping(value="updateTranCode")
	public ModelAndView updateTranCode( @ModelAttribute Purchase purchase) throws Exception{

		System.out.println("/updateTranCode.do");
		//Business Logic
		purchaseService.updateTranCode(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/purchase/listPurchase");
		
		return modelAndView;
	}
	
	@RequestMapping(value="deletePurchase")
	public ModelAndView deletePurchase( @RequestParam int tranNo) throws Exception{

		System.out.println("/deletePurchase.do");
		//Business Logic
		purchaseService.deletePurchase(tranNo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/purchase/listPurchase");
		
		return modelAndView;
	}

}
package com.model2.mvc.service.purchase.test;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("PurchaseServiceimpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	//@Test
	public void testAddPurchase() throws Exception {
		Purchase p = new Purchase();
		User user = new User();
		user.setUserId("user01");
		
		Product product = new Product();
		product.setProdNo(10021);
		
		p.setBuyer(user);
		p.setPaymentOption("1");
		p.setReceiverName("receName");
		p.setReceiverPhone("recePhone");
		p.setDivyAddr("adr");
		p.setDivyRequest("req");
		p.setTranCode("1");
		p.setDivyDate(new Date(23, 8, 12));
		p.setPurchaseProd(product);
		
		System.out.println(purchaseService.addPurchase(p));
		
	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		int tranNo = 10003;
		Purchase p = new Purchase();
		//Purchase p = sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
		p = purchaseService.getPurchase(tranNo);
		System.out.println(p);
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception {
		Purchase p = new Purchase();
		p.setPaymentOption("3");
		p.setReceiverName("123123");
		p.setReceiverPhone("uprecePhone");
		p.setDivyAddr("upaddr");
		p.setDivyRequest("uprequest");
		p.setDivyDate(new Date(11,1,1));
		p.setTranNo(10003);
		
		//Assert.assertEquals(1, (int)sqlSession.update("PurchaseMapper.updatePurchase", p)); 
		purchaseService.updatePurchase(p);
		System.out.println(p);
	}
	
	
	
	//@Test
	public void testGetPurchaseList() throws Exception {
		Search search = new Search();
		String userId = "user01";
		search.setStartRowNum(1);
		search.setEndRowNum(4);
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("userId", userId);
		
		//List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
		List<Purchase> list = purchaseService.getPurchaseList(map);
		for(Purchase p : list) {
			System.out.println(p);
		}
	}
	
	
	//@Test
	public void testGetTotalCount() throws Exception {
		Search s = new Search();
		//Assert.assertEquals(4, (int)sqlSession.selectOne("PurchaseMapper.getTotalCount", s));
		//Assert.assertEquals(6, purchaseService.getTotalCount(s));
	}
	
	
	//@Test
	public void testDeletePurchase() throws Exception {
		int tranNo = 10006;
		//Assert.assertEquals(1, (int)sqlSession.delete("PurchaseMapper.deletePurchase", tranNo));
		//Assert.assertEquals(1, purchaseService.deletePurchase(tranNo));
		purchaseService.deletePurchase(tranNo);
		System.out.println("delete complete");
	}
	
	//@Test
	public void testUpdateTranCode() throws Exception {
		int tranNo = 10000;
		Purchase p = purchaseService.getPurchase(tranNo);
		System.out.println("p의 tranCode는"+p.getTranCode().trim()+"이다");
		System.out.println(p);
		Assert.assertEquals(1, sqlSession.update("PurchaseMapper.updateTranCode", p));
		
	}

}
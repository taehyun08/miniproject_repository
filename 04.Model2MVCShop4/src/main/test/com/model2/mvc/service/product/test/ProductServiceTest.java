package com.model2.mvc.service.product.test;

import java.sql.Date;
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
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
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
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	@Test
	public void testAddUser() throws Exception {
		
		
		
		//==> console 확인
		//System.out.println(user);
		int prodNo = 10020;
		Product p = sqlSession.selectOne("ProductMapper.getProduct", prodNo);
		System.out.println(p);
		
		//Assert.assertEquals(1, sqlSession.selectOne("productMapper.getProduct", prodNo));
	}
	
	//@Test
	public void testGetListProduct() throws Exception {
		Search search = new Search();
		search.setPage(3);
		search.setSearchCondition(null);
		search.setSearchKeyword(null);
		search.setStartRowNum(1);
		search.setEndRowNum(5);
		List<Product> p = sqlSession.selectList("ProductMapper.getProductList", search);
		for(Product pro : p) {
			System.out.println(pro);
		}
	}
	
	//@Test
	public void testAddProduct() throws Exception {
		Product p = new Product();
		p.setFileName("1");
		p.setManuDate("2");
		p.setPrice(123);
		p.setProdDetail("3");
		p.setProdName("4");
		p.setStock(321);
		int a = sqlSession.insert("ProductMapper.addProduct", p);
		System.out.println(a);
	}
	
	
	//@Test
	public void testUpdateProduct() throws Exception {
		Product p = new Product();
		p.setProdNo(10022);
		p.setFileName("5");
		p.setManuDate("5");
		p.setPrice(555);
		p.setProdDetail("5");
		p.setProdName("5");
		p.setStock(123);
		int a = sqlSession.insert("ProductMapper.updateProduct", p);
		System.out.println(a);
	}
	
	
	@Test
	public void testGetTotalCount() throws Exception {
		Search s = new Search();
		Assert.assertEquals(3, (int)sqlSession.selectOne("ProductMapper.getTotalCount", s));
	}
	
	
	
	
	
	

}
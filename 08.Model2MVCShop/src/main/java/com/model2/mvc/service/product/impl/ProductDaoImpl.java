package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ProductDaoImpl() {
	}
	
	public int insertProduct(Product product) throws Exception {
		System.out.println("insertProduct 호출완료");
		System.out.println("insertProduct의 product :: " + product);
		return sqlSession.insert("ProductMapper.addProduct",product);
	}

	public Product findProduct(int prodNo) throws Exception {
		System.out.println("findProduct 호출완료");
		// update쪽에 추가해야함
		//StringBuffer sql = new StringBuffer("UPDATE product SET views = views+1 WHERE prod_no = ?");

		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	public List<Object> getProductList(Search search) throws Exception {
		System.out.println("getProductList 호출완료");
		System.out.println("search.getSearchCondition() : " + search.getSearchCondition());
		System.out.println("search.getSearchKeyword() : " + search.getSearchKeyword());
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}

	public int updateProduct(Product product) throws Exception {
		System.out.println("updateProduct 호출완료");
		return sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
	
	@Override
	public List<String> getProductListName() throws Exception {
		return sqlSession.selectList("ProductMapper.getProductListName");
	}

}

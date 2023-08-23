package com.model2.mvc.service.product;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {
	
	public int insertProduct(Product productVO) throws Exception;
	
	public Product findProduct(int prod_no) throws Exception;
	
	public List<Object> getProductList(Search searchVO) throws Exception;

	public int updateProduct(Product productVO) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
	
}


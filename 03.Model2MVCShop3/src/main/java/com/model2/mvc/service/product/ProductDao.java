package com.model2.mvc.service.product;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {
	
	public void insertProduct(Product productVO) throws Exception;
	
	public Product findProduct(int prod_no) throws Exception;
	
	public HashMap<String,Object> getProductList(Search searchVO) throws Exception;

	public void updateProduct(Product productVO) throws Exception;
	
}


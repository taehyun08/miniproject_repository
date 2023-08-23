package com.model2.mvc.service.product;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductService {
	
	public Product addProduct(Product productVO) throws Exception;
	
	public Product getProduct(int prod_no) throws Exception;
	
	public HashMap<String, Object> getProductList(Search searchVO) throws Exception;
	
	public int updateProduct(Product productVO) throws Exception;
	
}

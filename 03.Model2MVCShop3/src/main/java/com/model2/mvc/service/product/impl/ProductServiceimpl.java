package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.user.dao.UserDAO;

public class ProductServiceimpl implements ProductService {
	
	private ProductDAO productDAO;

	public ProductServiceimpl() {
		productDAO = new ProductDAO();
	}

	@Override
	public Product addProduct(Product productVO) throws Exception {
		productDAO.insertProduct(productVO);
		return productVO;
	}

	@Override
	public Product getProduct(int prod_no) throws Exception {
		return productDAO.findProduct(prod_no);
	}

	@Override
	public HashMap<String, Object> getProductList(Search searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	@Override
	public void updateProduct(Product productVO) throws Exception {
		productDAO.updateProduct(productVO);
	}
	

}

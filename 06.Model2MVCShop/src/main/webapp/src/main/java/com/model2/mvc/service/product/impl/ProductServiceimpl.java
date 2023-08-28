package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.dao.UserDAO;

public class ProductServiceimpl implements ProductService {
	
	private ProductDAO productDAO;

	public ProductServiceimpl() {
		productDAO = new ProductDAO();
	}

	@Override
	public ProductVO addProduct(ProductVO productVO) throws Exception {
		productDAO.insertProduct(productVO);
		return productVO;
	}

	@Override
	public ProductVO getProduct(int prod_no) throws Exception {
		return productDAO.findProduct(prod_no);
	}

	@Override
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		productDAO.updateProduct(productVO);
	}
	

}

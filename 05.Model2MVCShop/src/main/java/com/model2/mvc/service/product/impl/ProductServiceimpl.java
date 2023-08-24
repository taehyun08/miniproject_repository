package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Service
public class ProductServiceimpl implements ProductService {
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDaoImpl productDao;
	
	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}

	public ProductServiceimpl() {
		productDao = new ProductDaoImpl();
	}

	@Override
	public Product addProduct(Product productVO) throws Exception {
		productDao.insertProduct(productVO);
		return productVO;
	}

	@Override
	public Product getProduct(int prod_no) throws Exception {
		return productDao.findProduct(prod_no);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("list", productDao.getProductList(search));
		map.put("count", productDao.getTotalCount(search));
		return map;
	}

	@Override
	public int updateProduct(Product productVO) throws Exception {
		return productDao.updateProduct(productVO);
	}
	

}

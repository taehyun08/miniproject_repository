package com.model2.mvc.service.product.impl;

import java.util.HashMap;

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
	public HashMap<String, Object> getProductList(Search search) throws Exception {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("list", productDao.getProductList(search));
		hashMap.put("count", productDao.getTotalCount(search));
		return hashMap;
	}

	@Override
	public int updateProduct(Product productVO) throws Exception {
		return productDao.updateProduct(productVO);
	}
	

}

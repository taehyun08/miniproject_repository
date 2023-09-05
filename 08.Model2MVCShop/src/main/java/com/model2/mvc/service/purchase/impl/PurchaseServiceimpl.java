package com.model2.mvc.service.purchase.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceimpl implements PurchaseService {
	//field
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	PurchaseDao dao;
	
	@Autowired
	@Qualifier("productDaoImpl")
	ProductDao prodDao;
	//constructor
	public PurchaseServiceimpl() {
		dao = new PurchaseDaoImpl();
		prodDao = new ProductDaoImpl();
	}
	
	public void setDao(PurchaseDaoImpl dao) {
		this.dao = dao;
	}
	
	public void setProdDao(ProductDaoImpl prodDao) {
		this.prodDao = prodDao;
	}
	
	//method
	@Override
	public Purchase addPurchase(Purchase purchase) throws Exception {
		dao.insertPurchase(purchase);
		return purchase;
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return dao.findPurchase(tranNo);
	}

	@Override
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception {
		return dao.getPurchaseList(map);
	}

	@Override
	public Purchase updatePurchase(Purchase purchase) throws Exception {
		dao.updatePurchase(purchase);
		return purchase;
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		dao.updateTranCode(purchase);
	}

	@Override
	public void deletePurchase(int tranNo) throws Exception {
		dao.deletePurchase(tranNo);
	}
	
	@Override
	public int getTotalCount(Search search) throws Exception {
		return dao.getTotalCount(search);
	}

}

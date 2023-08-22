package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

public class PurchaseServiceimpl implements PurchaseService {
	//field
	PurchaseDAO dao;
	ProductDaoImpl prodDAO;
	//constructor
	public PurchaseServiceimpl() {
		dao = new PurchaseDAO();
		prodDAO = new ProductDaoImpl();
	}
	
	//method
	@Override
	public Purchase addPurchase(Purchase purchaseVO) throws Exception {
		dao.insertPurchase(purchaseVO);
		return purchaseVO;
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return dao.findPurchase(tranNo);
	}

	@Override
	public HashMap<String, Object> getPurchaseList(Search searchVO, String str) throws Exception {
		return dao.getPurchaseList(searchVO, str);
	}

	@Override
	public HashMap<String, Object> getSaleList(Search searchVO) throws Exception {
		return dao.getSaleList(searchVO);
	}

	@Override
	public Purchase updatePurchase(Purchase purchaseVO) throws Exception {
		dao.updatePurchase(purchaseVO);
		return purchaseVO;
	}

	@Override
	public void updateTranCode(Purchase purchaseVO) throws Exception {
		dao.updateTranCode(purchaseVO);
	}

	@Override
	public void deletePurchase(Purchase purchaseVO) throws Exception {
		dao.deletePurchase(purchaseVO);
	}
	
	

}

package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceimpl implements PurchaseService {
	//field
	PurchaseDAO dao;
	ProductDAO prodDAO;
	//constructor
	public PurchaseServiceimpl() {
		dao = new PurchaseDAO();
		prodDAO = new ProductDAO();
	}
	
	//method
	@Override
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception {
		dao.insertPurchase(purchaseVO);
		return purchaseVO;
	}

	@Override
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		return dao.findPurchase(tranNo);
	}

	@Override
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String str) throws Exception {
		return dao.getPurchaseList(searchVO, str);
	}

	@Override
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		return dao.getSaleList(searchVO);
	}

	@Override
	public PurchaseVO updatePurchase(PurchaseVO purchaseVO) throws Exception {
		dao.updatePurchase(purchaseVO);
		return purchaseVO;
	}

	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		dao.updateTranCode(purchaseVO);
	}

}

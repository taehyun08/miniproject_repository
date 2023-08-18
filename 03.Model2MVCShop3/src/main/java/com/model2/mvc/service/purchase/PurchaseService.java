package com.model2.mvc.service.purchase;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;



public interface PurchaseService {
	
	public Purchase addPurchase(Purchase purchaseVO) throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public HashMap<String, Object> getPurchaseList(Search searchVO,String userId) throws Exception;
	
	public HashMap<String, Object> getSaleList(Search searchVO) throws Exception;
	
	public Purchase updatePurchase(Purchase purchaseVO) throws Exception;
	
	public void updateTranCode(Purchase purchaseVO) throws Exception;
	
	public void deletePurchase(Purchase purchaseVO) throws Exception;
	
}

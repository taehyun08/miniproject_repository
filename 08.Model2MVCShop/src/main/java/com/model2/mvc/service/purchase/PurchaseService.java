package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;



public interface PurchaseService {
	
	public Purchase addPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception;
		
	public Purchase updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
	
	public void deletePurchase(int tranNo) throws Exception;
	
	public int getTotalCount(String userId) throws Exception;
	
}

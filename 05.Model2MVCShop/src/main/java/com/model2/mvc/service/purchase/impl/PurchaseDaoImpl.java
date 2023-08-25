package com.model2.mvc.service.purchase.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {}
	
	public Purchase findPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}
	
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}
	
	public void insertPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
		
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("updateTranCode", purchase);
	}
	
	public void deletePurchase(int tranNo) throws Exception {
		sqlSession.delete("PurchaseMapper.deletePurchase", tranNo);
	}
	
	public int getTotalCount(Search search) throws Exception{
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", search);
	}
}

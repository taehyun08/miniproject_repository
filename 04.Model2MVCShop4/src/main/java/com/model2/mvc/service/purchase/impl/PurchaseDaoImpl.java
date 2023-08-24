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

@Repository("PurchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {}
	
	public Purchase findPurchase(int tranNo) throws Exception {
		System.out.println("dao�� findPurchase �޼��� ȣ��");
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}
	
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception {
		System.out.println("getPurchaseList ȣ��Ϸ�");
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}
	
	public void insertPurchase(Purchase purchase) throws Exception {
		System.out.println("insertPurchase ȣ��Ϸ�");
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
		
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		System.out.println("updatePurchase ȣ��Ϸ�");
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}
	
	public void updateTranCode(Purchase purchaseVO) throws Exception {
		System.out.println("updateTranCode ȣ��Ϸ�");
		Connection con = DBUtil.getConnection();
		System.out.println(purchaseVO);
		String tranCode = "";
		int inputNo = -1;
		if(purchaseVO.getTranCode().equals("2")) {
			tranCode = "3";
			inputNo = purchaseVO.getPurchaseProd().getProdNo();
		}else {
			tranCode = "4";
			inputNo = purchaseVO.getTranNo();
		}
		String sql = "UPDATE transaction SET tran_status_code = ? ";
		if(purchaseVO.getTranCode().equals("3")) {
			sql += " WHERE tran_no = ? ";
		}else {
			sql += " WHERE prod_no = ? ";
		}
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, tranCode);
		stmt.setInt(2, inputNo);
		
		stmt.executeUpdate();
		
		con.close();
		System.out.println("updateTranCode ��");
	}
	
	public void deletePurchase(int tranNo) throws Exception {
		System.out.println("deletePurchase ȣ��Ϸ�");
		sqlSession.delete("PurchaseMapper.deletePurchase", tranNo);
	}
	
	public int getTotalCount(Search search) throws Exception{
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", search);
	}
}

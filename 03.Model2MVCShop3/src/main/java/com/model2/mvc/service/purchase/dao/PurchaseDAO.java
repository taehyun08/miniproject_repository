package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDAO {
	public PurchaseDAO() {}
	
	public Purchase findPurchase(int tranNo) throws Exception {
		System.out.println("dao의 findPurchase 메서드 호출");
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT tran_no , prod_no, buyer_id, payment_option, receiver_name, receiver_phone, demailaddr, dlvy_request, tran_status_code, "
				+ " order_date, dlvy_date "
				+ " FROM transaction WHERE tran_no = ?";
		System.out.println(tranNo);
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		
		ResultSet rs = stmt.executeQuery();
		
		Purchase purchaseVO = new Purchase();
		User userVO = new User();
		Product productVO = new Product();
		
		while(rs.next()) {
		userVO.setUserId(rs.getString("buyer_id"));
		productVO.setProdNo(rs.getInt("prod_no"));
		purchaseVO.setTranNo(rs.getInt("tran_no"));
		purchaseVO.setPaymentOption(rs.getString("payment_option"));
		purchaseVO.setReceiverName(rs.getString("receiver_name"));
		purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
		purchaseVO.setDivyAddr(rs.getString("demailaddr"));
		purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
		purchaseVO.setTranCode(rs.getString("tran_status_code"));
		purchaseVO.setOrderDate(rs.getDate("order_date"));
		String dlvyDate = "";
		if(rs.getDate("dlvy_date") == null) {
			
		}else {
			dlvyDate = rs.getDate("dlvy_date").toString();
		}
		purchaseVO.setDivyDate(dlvyDate);
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPurchaseProd(productVO);
		}
		System.out.println(purchaseVO);
		// orderdate 형식 봐야함
		
		rs.close();
		con.close();
		System.out.println("dao의 findPurchase 메서드 끝");
		return purchaseVO;
	}
	
	public HashMap<String, Object> getPurchaseList(Search searchVO, String userId) throws Exception {
		System.out.println("getPurchaseList 호출완료");
		Connection con = DBUtil.getConnection();
		
		String subsql ="SELECT p.prod_name, t.tran_no , t.prod_no, t.buyer_id, t.payment_option, t.receiver_name, t.receiver_phone, t.demailaddr, t.dlvy_request, t.tran_status_code, "
				+ " t.order_date, t.dlvy_date, COUNT(*) OVER() as total_row, "
				+ " ROW_NUMBER() OVER (order by t.tran_no) as r "
				+ " from product p, transaction t, users u WHERE p.prod_no = t.prod_no AND u.user_id = t.buyer_id AND u.user_id = ? order by t.tran_no";
		
		String sql = "SELECT sub.* FROM ( " + subsql + " ) sub WHERE sub.r >= ? AND sub.r <= ?";
		
		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1, userId);
		
		System.out.println("page값: "+ searchVO.getPage());
		int startIndex = searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1;
		System.out.println("startindex 값: "+ startIndex);
		System.out.println("endindex 값: "+ (startIndex + searchVO.getPageUnit() - 1));
		stmt.setInt(2, startIndex);
		stmt.setInt(3, startIndex + searchVO.getPageUnit() - 1);
		
		
		ResultSet rs = stmt.executeQuery();

		rs.next();
		int total = rs.getInt("total_row");
		System.out.println("로우의 수:" + total);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		//rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<Purchase> list = new ArrayList<Purchase>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				Purchase vo = new Purchase();
				Product productVO = new Product();
				User userVO = new User();
				
				productVO.setProdName(rs.getString("prod_name"));
				productVO.setProdNo(rs.getInt("prod_no"));
				userVO.setUserId(rs.getString("buyer_id"));

				vo.setTranNo(rs.getInt("tran_no"));
				vo.setPaymentOption(rs.getString("payment_option"));
				vo.setReceiverName(rs.getString("receiver_name"));
				vo.setReceiverPhone(rs.getString("receiver_phone"));
				vo.setDivyAddr(rs.getString("demailaddr"));
				vo.setDivyRequest(rs.getString("dlvy_request"));
				vo.setTranCode(rs.getString("tran_status_code"));
				vo.setOrderDate(rs.getDate("order_date"));
				vo.setDivyDate(rs.getString("dlvy_date"));
				vo.setBuyer(userVO);
				vo.setPurchaseProd(productVO);

				list.add(vo);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
		System.out.println("getPurchaseList 끝");
		return map;
	}
	
	public HashMap<String, Object> getSaleList(Search searchVO) throws Exception {
		System.out.println("getSaleList 호출완료");
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from transaction WHERE tran_status_code IN ('1','2','3') order by prod_no";

		PreparedStatement stmt = con.prepareStatement(sql);
		con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<Purchase> list = new ArrayList<Purchase>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				Purchase vo = new Purchase();
				Product productVO = new Product();
				User userVO = new User();
				
				
				productVO.setProdNo(rs.getInt("prod_no"));
				userVO.setUserId(rs.getString("buyer_id"));
				
				vo.setTranNo(rs.getInt("tran_no"));
				vo.setPaymentOption(rs.getString("payment_option"));
				vo.setReceiverName(rs.getString("receiver_name"));
				vo.setReceiverPhone(rs.getString("receiver_phone"));
				vo.setDivyAddr(rs.getString("demailaddr"));
				vo.setDivyRequest(rs.getString("dlvy_request"));
				vo.setTranCode(rs.getString("tran_status_code"));
				vo.setOrderDate(rs.getDate("order_date"));
				vo.setDivyDate(rs.getString("dlvy_date"));
				vo.setBuyer(userVO);
				vo.setPurchaseProd(productVO);

				list.add(vo);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
		System.out.println("getSaleList 끝");
		return map;
	}
	
	public void insertPurchase(Purchase purchaseVO) throws Exception {
		System.out.println("insertPurchase 호출완료");
		Connection con = DBUtil.getConnection();
		System.out.println(purchaseVO);
		String sql = "INSERT INTO transaction VALUES(seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setString(8, purchaseVO.getTranCode());
		stmt.setString(9, purchaseVO.getDivyDate());
		
		stmt.executeUpdate();
		
		con.close();
		System.out.println("insertPurchase 끝");
		
	}
	
	public void updatePurchase(Purchase purchaseVO) throws Exception {
		System.out.println("updatePurchase 호출완료");
		Connection con = DBUtil.getConnection();
		System.out.println(purchaseVO);
		String sql = "UPDATE transaction SET payment_option = ?, receiver_name = ?, receiver_phone = ?, demailaddr = ?, dlvy_request = ?, dlvy_date = ? "
				+ " WHERE tran_no = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getPaymentOption());
		stmt.setString(2, purchaseVO.getReceiverName());
		stmt.setString(3, purchaseVO.getReceiverPhone());
		stmt.setString(4, purchaseVO.getDivyAddr());
		stmt.setString(5, purchaseVO.getDivyRequest());
		stmt.setDate(6, purchaseVO.getOrderDate());
		stmt.setInt(7, purchaseVO.getTranNo());
		
		stmt.executeUpdate();
		
		con.close();
		System.out.println("updatePurchase 끝");
	}
	
	public void updateTranCode(Purchase purchaseVO) throws Exception {
		System.out.println("updateTranCode 호출완료");
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
		System.out.println("updateTranCode 끝");
	}
	
	public void deletePurchase(Purchase purchaseVO) throws Exception {
		System.out.println("deletePurchase 호출완료");
		Connection con = DBUtil.getConnection();
		System.out.println(purchaseVO.getTranNo());
		StringBuffer sql = new StringBuffer("DELETE transaction WHERE tran_no = ?");
		PreparedStatement stmt = con.prepareStatement(sql.toString());
		stmt.setInt(1, purchaseVO.getTranNo());
		stmt.executeUpdate();

		
		con.close();
		System.out.println("deletePurchase 끝");
	}
}

package com.model2.mvc.service.product.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

public class ProductDaoImpl implements ProductDao {

	public ProductDaoImpl() {
	}
	
	public void insertProduct(Product productVO) throws Exception {
		System.out.println("insertProduct 호출완료");
		Connection con = DBUtil.getConnection();
		System.out.println(productVO);
		String sql = "INSERT INTO product VALUES(seq_product_prod_no.nextval, ?, ?, ?, ?, ?, sysdate, 0, ?)";
		String manuDate = productVO.getManuDate().replace("-", "");
		System.out.println(manuDate);
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, manuDate);
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getStock());
		
		stmt.executeUpdate();
		
		con.close();
		System.out.println("insertProduct 끝");
	}

	public Product findProduct(int prod_no) throws Exception {
		System.out.println("findProduct 호출완료");
		Connection con = DBUtil.getConnection();
		System.out.println(prod_no);
		StringBuffer sql = new StringBuffer("UPDATE product SET views = views+1 WHERE prod_no = ?");
		PreparedStatement stmt1 = con.prepareStatement(sql.toString());
		System.out.println(sql.toString());
		stmt1.setInt(1, prod_no);
		stmt1.executeUpdate();
		
		sql.setLength(0);
		
		sql.append("SELECT p.prod_no, p.prod_name, p.prod_detail, p.manufacture_day, p.price, p.image_file, p.reg_date, t.tran_status_code, p.views, p.stock "
				+ " FROM product p "
				+ " LEFT JOIN transaction t "
				+ " ON p.prod_no = t.prod_no "
				+ " WHERE p.prod_no = ?");
		
		PreparedStatement stmt = con.prepareStatement(sql.toString());
		stmt.setInt(1, prod_no);
		
		ResultSet rs = stmt.executeQuery();
		
		Product productVO = new Product();
		while (rs.next()) {
			productVO.setProdNo(rs.getInt("prod_no"));
			productVO.setProdName(rs.getString("prod_name"));
			productVO.setProdDetail(rs.getString("prod_detail"));
			productVO.setManuDate(rs.getString("manufacture_day"));
			productVO.setPrice(rs.getInt("price"));
			productVO.setFileName(rs.getString("image_file"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
			productVO.setProTranCode(rs.getString("tran_status_code"));
			productVO.setViews(rs.getInt("views"));
			productVO.setStock(rs.getInt("stock"));
		}
		
		
		rs.close();
		con.close();
		System.out.println("findProduct 끝");
		return productVO;
	}

	public HashMap<String,Object> getProductList(Search searchVO) throws Exception {
		System.out.println("getProductList 호출완료");
		Connection con = DBUtil.getConnection();
		
		// 정렬을 방식을 위한 코드
		String orderBy = searchVO.getOrderBy();
		if(orderBy.equals("prodNo")) {
			orderBy = " order by p.prod_no";
		}else if(orderBy.equals("priceDesc")) {
			orderBy = " order by p.price DESC";
		}else if(orderBy.equals("priceAsce")) {
			orderBy = " order by p.price";
		}else if(orderBy.equals("viewsDesc")) {
			orderBy = " order by p.views DESC";
		}else if(orderBy.equals("nameAsce")) {
			orderBy = " order by p.prod_name";
		}
		
		String subsql = "select p.prod_no, p.prod_name, p.prod_detail, p.manufacture_day, p.price, p.image_file, p.reg_date, t.tran_status_code, p.views, p.stock, COUNT(*) OVER() as total_row "
				+ ", ROW_NUMBER() OVER (" + orderBy + ") as r "
				+ " from product p LEFT JOIN transaction t ON p.prod_no = t.prod_no ";
		String searchKeyword = searchVO.getSearchKeyword();
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				if(searchKeyword == "") {
					searchKeyword = "-1";
				}
				subsql += " WHERE p.prod_no = " + searchKeyword;
			} else if (searchVO.getSearchCondition().equals("1")) {
				searchKeyword = "%" + searchKeyword + "%";
				subsql += " WHERE p.prod_name LIKE ?";
			} else if (searchVO.getSearchCondition().equals("2")) {
				if(searchKeyword == "") {
					searchKeyword = "-1";
				}
				subsql += " WHERE p.price = " + searchKeyword;
	}
		}
		
		
		String sql = "SELECT sub.* FROM ( " + subsql + " ) sub WHERE sub.r >= ? AND sub.r <= ?";
		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		System.out.println("page값: "+ searchVO.getPage());
		int startIndex = searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1;
		System.out.println("startindex 값: "+ startIndex);
		System.out.println("endindex 값: "+ (startIndex + searchVO.getPageUnit() - 1));
		
		if(searchVO.getSearchCondition() != null && searchVO.getSearchCondition().equals("1")) {
			stmt.setString(1, searchKeyword);
			stmt.setInt(2, startIndex);
			stmt.setInt(3, startIndex + searchVO.getPageUnit() - 1);
		}else {
			stmt.setInt(1, startIndex);
			stmt.setInt(2, startIndex + searchVO.getPageUnit() - 1);
		}
		ResultSet rs = stmt.executeQuery();
		System.out.println("getProductList의 쿼리 실행완료");
		rs.next();
		int total = rs.getInt("total_row");
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		//rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<Product> list = new ArrayList<Product>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				Product vo = new Product();
				vo.setProdNo(rs.getInt("prod_no"));
				vo.setProdName(rs.getString("prod_name"));
				vo.setProdDetail(rs.getString("prod_detail"));
				vo.setManuDate(rs.getString("manufacture_day"));
				vo.setPrice(rs.getInt("price"));
				vo.setFileName(rs.getString("image_file"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				vo.setViews(rs.getInt("views"));
				vo.setStock(rs.getInt("stock"));
				String trnaStatusCode = rs.getString("tran_status_code");
				if(trnaStatusCode == null) {
					vo.setProTranCode("1");
				}else {
					vo.setProTranCode(trnaStatusCode);
				}
				
				
				list.add(vo);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		rs.close();
		con.close();
		System.out.println("getProductList 끝");
		return map;
	}

	public void updateProduct(Product productVO) throws Exception {
		System.out.println("updateProduct 호출완료");
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product SET prod_name = ?, prod_detail = ?, manufacture_day = ?, price = ?, image_file = ?, stock = ?  WHERE prod_no = ?";
		String manuDate = productVO.getManuDate().replace("-", "");
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, manuDate);
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getStock());
		stmt.setInt(7, productVO.getProdNo());
		
		stmt.executeUpdate();
		System.out.println("updateProduct 끝");
		con.close();
	}

}

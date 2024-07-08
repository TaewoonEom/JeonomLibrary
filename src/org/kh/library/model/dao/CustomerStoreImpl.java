package org.kh.library.model.dao;

import java.sql.*;
import java.util.*;

import org.kh.library.model.vo.Customer;

public class CustomerStoreImpl implements CustomerStore {

	@Override
	public List<Customer> selectAllCustomer(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rset = null;
		List<Customer> cList = new ArrayList<Customer>();
		String query = "SELECT * FROM CUSTOMER";
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		while(rset.next()) {
			Customer ctm = this.rsetToCustomer(rset);
			cList.add(ctm);
		}
		conn.close();
		stmt.close();
		rset.close();
		return cList;
	}

	@Override
	public Customer selectNameSearch(String CName, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Customer ctm = null;
		String query = "SELECT * FROM CUSTOMER WHERE USER_NAME = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, CName);
		rset = pstmt.executeQuery();
		if(rset.next()) ctm = rsetToCustomer(rset);
		return ctm;
	}

	@Override
	public Customer selectIdSearch(String CId, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Customer ctm = null;
		String query = "SELECT * FROM CUSTOMER WHERE USER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, CId);
		rset = pstmt.executeQuery();
		if(rset.next()) ctm = rsetToCustomer(rset);
		return ctm;
	}

	@Override
	public int insertCustomer(Customer ctm, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO CUSTOMER VALUES(SEQ_CUSTOMER_NO.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, ctm.getUserId());
		pstmt.setString(2, ctm.getUserName());
		pstmt.setInt(3, ctm.getUserAge());
		pstmt.setString(4, ctm.getAddr());
		pstmt.setString(5, ctm.getGender());
		result = pstmt.executeUpdate();
		conn.close();
		pstmt.close();
		return result;
	}

	@Override
	public int updateCustomer(Customer ctm, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE CUSTOMER SET USER_AGE = ?, ADDR = ? WHERE USER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ctm.getUserAge());
		pstmt.setString(2, ctm.getAddr());
		pstmt.setString(3, ctm.getUserId());
		result = pstmt.executeUpdate();
		conn.close();
		pstmt.close();
		return result;
	}

	@Override
	public int deleteCustomer(String CId, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM CUSTOMER WHERE USER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, CId);
		result = pstmt.executeUpdate();
		conn.close();
		pstmt.close();
		return result;
	}

	private Customer rsetToCustomer(ResultSet rset) throws SQLException {
		Customer ctm = new Customer();
		ctm.setUserNO(rset.getInt("USER_NO"));
		ctm.setUserId(rset.getString("USER_ID"));
		ctm.setUserName(rset.getString("USER_NAME"));
		ctm.setUserAge(rset.getInt("USER_AGE"));
		ctm.setAddr(rset.getString("ADDR"));
		ctm.setGender(rset.getString("GENDER"));
		ctm.setEnrollDate(rset.getDate("ENROLL_DATE"));
		return ctm;
	}

}

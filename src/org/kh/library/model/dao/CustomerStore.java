package org.kh.library.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.kh.library.model.vo.Customer;

public interface CustomerStore {
	public List<Customer> selectAllCustomer(Connection conn) throws SQLException;
	public Customer selectNameSearch(String CName, Connection conn) throws SQLException;
	public Customer selectIdSearch(String CId, Connection conn) throws SQLException;
	public int insertCustomer(Customer customer, Connection conn) throws SQLException;
	public int updateCustomer(Customer customer, Connection conn) throws SQLException;
	public int deleteCustomer(String CId, Connection conn) throws SQLException;
}

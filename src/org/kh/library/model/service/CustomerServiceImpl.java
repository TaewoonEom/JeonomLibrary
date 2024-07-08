package org.kh.library.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.kh.library.common.JDBCTemplate;
import org.kh.library.model.dao.CustomerStoreImpl;
import org.kh.library.model.vo.Customer;

public class CustomerServiceImpl implements CustomerService{
	private CustomerStoreImpl cStore;
	
	public CustomerServiceImpl() {
		cStore = new CustomerStoreImpl();
	}
	
	@Override
	public List<Customer> selectAllCustomer() {
		Connection conn = null;
		List<Customer> cList = null;
		try {
			conn = JDBCTemplate.getConnection();
			cList = cStore.selectAllCustomer(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cList;
	}

	@Override
	public Customer selectNameSearch(String CName) {
		Connection conn = null;
		Customer cInfo = null;
		try {
			conn = JDBCTemplate.getConnection();
			cInfo = cStore.selectNameSearch(CName, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cInfo;
	}

	@Override
	public Customer selectIdSearch(String CId) {
		Connection conn = null;
		Customer cInfo = null;
		try {
			conn = JDBCTemplate.getConnection();
			cInfo = cStore.selectIdSearch(CId, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cInfo;
	}

	@Override
	public int insertCustomer(Customer customer) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = cStore.insertCustomer(customer, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int updateCustomer(Customer customer) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = cStore.updateCustomer(customer, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int deleteCustomer(String CId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = cStore.deleteCustomer(CId, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}

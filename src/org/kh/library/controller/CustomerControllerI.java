package org.kh.library.controller;

import java.util.List;

import org.kh.library.model.vo.Customer;

public interface CustomerControllerI {
	public List<Customer> selectAllCustomer();
	public Customer selectNameSearch(String CName);
	public Customer selectIdSearch(String CId);
	public int insertCustomer(Customer customer);
	public int updateCustomer(Customer customer);
	public int deleteCustomer(String CId);
}

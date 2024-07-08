package org.kh.library.controller;

import java.util.List;

import org.kh.library.model.service.CustomerServiceImpl;
import org.kh.library.model.vo.Customer;

public class CustomerController implements CustomerControllerI{
	private CustomerServiceImpl cService;
	
	public CustomerController() {
		cService = new CustomerServiceImpl();
	}
	
	@Override
	public List<Customer> selectAllCustomer() {
		List<Customer> cList = cService.selectAllCustomer();
		return cList;
	}

	@Override
	public Customer selectNameSearch(String CName) {
		Customer cInfo = cService.selectNameSearch(CName);
		return cInfo;
	}

	@Override
	public Customer selectIdSearch(String CId) {
		Customer cInfo = cService.selectIdSearch(CId);
		return cInfo;
	}

	@Override
	public int insertCustomer(Customer customer) {
		int result = cService.insertCustomer(customer);
		return result;
	}

	@Override
	public int updateCustomer(Customer customer) {
		int result = cService.updateCustomer(customer);
		return result;
	}

	@Override
	public int deleteCustomer(String CId) {
		int result = cService.deleteCustomer(CId);
		return result;
	}

}

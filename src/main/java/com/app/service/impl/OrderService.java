package com.app.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Customer;
import com.app.repo.CustomerRepository;
import com.app.repo.VehicleRepository;
import com.app.service.IOrderService;
@Service
public class OrderService implements IOrderService{

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired 
	private VehicleRepository vehicleRepository;

	public Date saveOrder(Customer c) {
		return (Date) customerRepository.save(c).getDate();
	}

	public List<Customer> getAllOrder() {
		return customerRepository.findAll();
	}


	public Optional<Customer> getOneOrder(Date d) {
		return customerRepository.findById(d);
	}


	public void deleteOrder(Date d) {
		customerRepository.deleteById(d);
	}

	public boolean isExist(Date d) {
		return customerRepository.existsById(d);
	}
}

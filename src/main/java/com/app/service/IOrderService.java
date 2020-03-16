package com.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.app.model.Customer;

public interface IOrderService {
    
	public Date saveOrder(Customer c);

	public List<Customer> getAllOrder() ;


	public Optional<Customer> getOneOrder(Date d);


	public void deleteOrder(Date d) ;

	public boolean isExist(Date d) ;
}

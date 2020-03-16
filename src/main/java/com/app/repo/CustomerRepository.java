package com.app.repo;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Date> {
//     @Query("SELECT c.date,v. FROM Customer c WHERE ")
//	public String getInfoAccDate();
}

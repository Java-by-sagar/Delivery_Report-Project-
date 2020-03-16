package com.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Customer;
import com.app.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderRestController {
	@Autowired
	private IOrderService Service;

	@PostMapping("/save")
	public ResponseEntity<String> saveOrder(
			@RequestBody Customer customer)
	{
		ResponseEntity<String> resp=null;
		try {
			java.util.Date date=Service.saveOrder(customer);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(date);  
			resp=new ResponseEntity<String>(strDate+" ->SAVED",HttpStatus.OK);
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to Save",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllOrder(){
		ResponseEntity<?> resp=null;
		try {
			List<Customer> list=Service.getAllOrder();
			if(list!=null && !list.isEmpty())
				resp=new ResponseEntity<List<Customer>>(list,HttpStatus.OK);
			else
				resp=new ResponseEntity<String>("No Data Found",HttpStatus.OK);
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to fetch Data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	@GetMapping("/one/{str}")
	public ResponseEntity<?> getOneOrder(
			@PathVariable String str) throws ParseException
	{
		Date date=new SimpleDateFormat("yyyy-MM-dd").parse(str);  
		ResponseEntity<?> resp=null;
		try {
			Optional<Customer> opt=Service.getOneOrder(date);
			if(opt.isPresent())
				resp=new ResponseEntity<Customer>(opt.get(),HttpStatus.OK);
			else
				resp=new ResponseEntity<String>("No Data Found",HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to Fetch Data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	@DeleteMapping("/remove/{str}")
	public ResponseEntity<String> deleteOrder(
			@PathVariable String str) throws ParseException
	{
		String str1=str;  
		Date date=new SimpleDateFormat("yyyy-MM-dd").parse(str1); 
		ResponseEntity<String> resp=null;
		try {
			boolean exist=Service.isExist(date);
			if(exist) {
				Service.deleteOrder(date);
				resp=new ResponseEntity<String>("Details of date:"+str+"-removed",HttpStatus.OK);
			}else {

				resp=new ResponseEntity<String>(str+"-Not Exist",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to Delete..",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateOrderAccoDate(
			@RequestBody Customer customer)
	{
		ResponseEntity<String> resp=null;
		try {
			boolean exist=Service.isExist(customer.getDate());
			if(exist) {
				Service.saveOrder(customer);
				resp=new ResponseEntity<String>(customer.getDate()+"-Updated",HttpStatus.OK);
			}else {
				resp=new ResponseEntity<String>(customer.getDate()+"-Not Exist",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to Update",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

}

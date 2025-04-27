package com.rk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rk.dto.request.UserLoginRequest;
import com.rk.dto.request.UserRequest;
import com.rk.dto.response.UserResponse;
import com.rk.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/register", method =RequestMethod.POST )
	public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){
		UserResponse response=service.register(request);
		return new ResponseEntity<UserResponse>(response,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/login", method =RequestMethod.POST )
	public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest request){
		ResponseEntity<UserResponse> response=service.login(request);
		return response;
		
	}
	@RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserResponse> updateCustomer(@PathVariable int id,@RequestBody UserRequest request){
		UserResponse response=service.updateCustomer(id,request);
		return new ResponseEntity<UserResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public String deleteCustomer(@PathVariable int id,@RequestBody UserRequest request){
		String response=service.deleteCustomer(id,request);
		return response;
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserResponse> getUserById(@PathVariable int id){
	    UserResponse response = service.getuserById(id);
	    return new ResponseEntity<UserResponse>(response,HttpStatus.OK);
	}

}

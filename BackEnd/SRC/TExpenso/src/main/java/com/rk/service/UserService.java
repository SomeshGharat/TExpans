package com.rk.service;

import org.springframework.http.ResponseEntity;

import com.rk.dto.request.UserLoginRequest;
import com.rk.dto.request.UserRequest;
import com.rk.dto.response.UserResponse;

public interface UserService {

	UserResponse register(UserRequest request);

	ResponseEntity<UserResponse> login(UserLoginRequest request);

	UserResponse updateCustomer(int id, UserRequest request);

	String deleteCustomer(int id, UserRequest request);

	UserResponse getuserById(int id);

	//ResponseEntity<UserResponse> getuserDetailsByEmailAndPassword(UserLoginRequest request);

	

}

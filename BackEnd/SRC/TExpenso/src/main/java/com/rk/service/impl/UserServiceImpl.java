package com.rk.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rk.dto.request.UserLoginRequest;
import com.rk.dto.request.UserRequest;
import com.rk.dto.response.UserResponse;
import com.rk.entity.User;
import com.rk.repositery.UserRepositery;
import com.rk.service.UserService;
import com.rk.util.Status;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepositery repo;
    public User ConvertedCoustomer(UserRequest request) {

		User coustomer = new User();
		coustomer.setU_name(request.getU_name());
		coustomer.setEmail(request.getEmail());
		coustomer.setMob_no(request.getMob_no());
		coustomer.setPassword(request.getPassword());
		

		User response = repo.save(coustomer);
		return response;
	}

    // Converts a User entity to a UserResponse DTO
    private UserResponse covertedToResponseFromUser(User reponse) {
        UserResponse cr = new UserResponse();
        cr.setReponse(reponse);
        cr.setStatus(Status.SUCCESS);
        return cr;
    }

    // Converts a UserRequest DTO to a User entity
    public User ConvertedUser(UserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setMob_no(request.getMob_no());
        user.setPassword(request.getPassword());
        user.setU_name(request.getU_name());
        return user;
    }

    

    @Override
    public ResponseEntity<UserResponse> login(UserLoginRequest request) {
        User user = repo.findByEmailAndPassword(request.getEmail(), request.getPassword());
        
        if (user != null) {
            UserResponse response = covertedToResponseFromUser(user);
            return ResponseEntity.ok(response); // Use ResponseEntity with appropriate status
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponse(Status.FAIL, null));
        }
    }
	
	


    // Update Customer
    @Override
    public UserResponse updateCustomer(int id, UserRequest request) {
        try {
            User user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
            user.setEmail(request.getEmail());
            user.setMob_no(request.getMob_no());
            user.setPassword(request.getPassword());
            user.setU_name(request.getU_name());
            repo.save(user);
            return covertedToResponseFromUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new UserResponse(Status.FAIL, null);
        }
    }

    // Delete Customer
    @Override
    public String deleteCustomer(int id, UserRequest request) {
        try {
            User user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
            repo.delete(user);
            return "Record Deleted Sucussfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Something Went Wrong While Deleting The Record";
        }
    }
    
    
 // Register
    
    @Autowired
    private BalanceServiceImpl balanceServiceImpl;
    @Override
    public UserResponse register(UserRequest request) {
        User email = repo.findByEmail(request.getEmail());
        if (email == null) {
            User customer = ConvertedCoustomer(request);

            // After user is saved, now create Balance entry
            int userid=customer.getUid();
            balanceServiceImpl.initBalance(userid);
            return covertedToResponseFromUser(customer);
        }

        UserResponse fail = new UserResponse();
        fail.setStatus(Status.FAIL);
        return fail;
    }


	@Override
	public UserResponse getuserById(int id) {
	    Optional<User> userOptional = repo.findById(id);
	    
	    if (userOptional.isPresent()) {
	        UserResponse userResponse = covertedToResponseFromUser(userOptional.get());
	        return userResponse;
	    } else {
	        return null; // Proper error response
	    }
	}

}
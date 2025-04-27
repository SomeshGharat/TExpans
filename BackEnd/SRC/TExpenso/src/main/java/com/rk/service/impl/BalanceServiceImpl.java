package com.rk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.entity.Balance;
import com.rk.entity.User;
import com.rk.repositery.BalanceRepositery;
import com.rk.repositery.UserRepositery;
import com.rk.service.BalanceService;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceRepositery repo;
    
    @Autowired
    private UserRepositery urepo;
    
    @Override
    public double getCurrentBalance(int id) {
        try {
            User u=urepo.findById(id).get();
            Balance user = repo.findByUser(u);
            // Return current balance
            return user.getBalance();
        } catch (Exception e) {
            
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch current balance: " + e.getMessage());
        }
    }
    
    public String initBalance(int uid) {
    	Balance balance=new Balance();
    	User user=new User();
    	user.setUid(uid);
    	balance.setUserid(user);
    	repo.save(balance);
		return "Balance created sucussfully";
    	
    }

	
}
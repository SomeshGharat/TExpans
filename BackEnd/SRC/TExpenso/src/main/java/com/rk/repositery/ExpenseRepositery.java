package com.rk.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.entity.Expense;
@Repository
public interface ExpenseRepositery extends JpaRepository<Expense, Integer>{

	List<Expense> findByUserUid(int uid);


}

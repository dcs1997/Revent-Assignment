package com.masai.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masai.Exception.UserException;
import com.masai.Model.User;
import com.masai.Repository.UserRepo;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers(Integer pageNumber) throws UserException {
		
		int pageSize = 2;
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		
		Page<User> pgpost = userRepo.findAll(p);
		
		List<User> li = pgpost.getContent();
		
		return li;
	}

	@Override
	public String deleteUser(Integer id) throws UserException {
		// TODO Auto-generated method stub
		
		User us = userRepo.findById(id).orElseThrow(() -> new UserException("User not found"));
		
		userRepo.delete(us);
		
		return "User deleted successfully!!!";
	}

	

}
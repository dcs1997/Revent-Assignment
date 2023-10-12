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
	
		return userRepo.save(user);
	}

	
	
	
	@Override
	public String deleteUser(Integer id) throws UserException {
		
		User us = userRepo.findById(id).orElseThrow(() -> new UserException("User not found"));
		
		userRepo.delete(us);
		
		return "User deleted successfully!!!";
	}

	
	
	
	@Override
	public User authenticateUser(User user) throws UserException {
		
		 User storedUser = userRepo.findByEmail(user.getEmail());

		    if (storedUser == null)   throw new UserException("User not found");
		    
		    if (!isPasswordMatch(user.getPassword(), storedUser.getPassword()))  throw new UserException("Incorrect password");
		    
		    return storedUser;		
	}

	
	
	private boolean isPasswordMatch(String providedPassword, String storedPassword) {
	   
	    return providedPassword.equals(storedPassword);
	}
	

}

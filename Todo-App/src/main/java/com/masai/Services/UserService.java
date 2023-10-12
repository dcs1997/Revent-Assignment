package com.masai.Services;

import java.util.List;


import com.masai.Exception.UserException;
import com.masai.Model.User;

public interface UserService {
	
	 public User addUser(User task);
		
	 public List<User> getAllUsers(Integer pageNumber) throws UserException;
	 
	 public String deleteUser(Integer id) throws UserException;

	public User authenticateUser(User user)throws UserException;
	
	

}

package com.masai.Services;
import com.masai.Exception.UserException;
import com.masai.Model.User;

public interface UserService {
	
	 public User addUser(User task);
	 
	 public String deleteUser(Integer id) throws UserException;

	 public User authenticateUser(User user)throws UserException;	

}

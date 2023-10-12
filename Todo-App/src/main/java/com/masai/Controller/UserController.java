package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.masai.Exception.UserException;

import com.masai.Model.User;
import com.masai.Services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	
	private UserService userServ;
	
	
	@PostMapping("/Users")
	public ResponseEntity<User> createUser(@RequestBody @Valid User user){
		
		User save = userServ.addUser(user);
		
		return new ResponseEntity<>(save, HttpStatus.OK);
		
	}
	
	@GetMapping("/Users")
	public ResponseEntity <List<User>> ListOfUser(@RequestParam @Valid Integer pageNumber) throws UserException{
		
		List<User> st = userServ.getAllUsers(pageNumber);
		return new ResponseEntity<>(st, HttpStatus.OK);
	}
	
	
	//To get all data at one time
//	@GetMapping("/AllUsers")
//	public ResponseEntity <List<User>> ListOfUser() throws UserException{
//		
//		List<User> st = userServ.getAllUsers(null);
//		return new ResponseEntity<>(st, HttpStatus.OK);
//	}
	
	
	
	@DeleteMapping("/Users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable @Valid Integer id) throws UserException{
		
		String str = userServ.deleteUser(id);
		
		return new ResponseEntity<>(str, HttpStatus.OK);
	}
	
	
}

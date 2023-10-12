package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

	
	
	@Autowired
	private UserService userServ;
	
	
	
	
	@PostMapping("/Users")
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) {

		User save = userServ.addUser(user);

		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	
	
	
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user) {
		try {

			User authenticatedUser = userServ.authenticateUser(user);

			return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
		}

		catch (UserException e) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	
	
	
	
	
	@DeleteMapping("/Users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable @Valid Integer id) throws UserException {

		String str = userServ.deleteUser(id);

		return new ResponseEntity<>(str, HttpStatus.OK);
	}

}

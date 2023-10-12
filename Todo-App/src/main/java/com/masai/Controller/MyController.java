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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.TaskException;
import com.masai.Exception.UserException;
import com.masai.Model.Task;
import com.masai.Services.TaskService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MyController {

		
	
	@Autowired
	private TaskService taskServ;
	
	
	
	
	
	
	@PostMapping("/Tasks/{id}")
	public ResponseEntity<String> createTask(@RequestBody @Valid Task task, @PathVariable Integer id) throws UserException{
		
		String save = taskServ.addTask(task, id);
		
		return new ResponseEntity<>(save, HttpStatus.OK);
		
	}
	
	
	
	
	@GetMapping("/Tasks/{userId}/page")
	public ResponseEntity <List<Task>> ListOfTasksUsingPaginationAndSorting( @PathVariable Integer userId, @RequestParam Integer pageNumber) throws TaskException, UserException{
		
		List<Task> st = taskServ.getAllTasksUsingPagination(userId, pageNumber);
		
		return new ResponseEntity<>(st, HttpStatus.OK);
	}
	
	
	
	
	@PutMapping("/Tasks/{id}")
	public ResponseEntity<Task> UpdateTask(@RequestBody @Valid Task task, @PathVariable Integer id) throws UserException, TaskException{
		
		Task save = taskServ.UpdateTask(task, id);
		
		return new ResponseEntity<>(save, HttpStatus.OK);
		
	}
	
	
	
	
	@DeleteMapping("/Tasks/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable @Valid Integer id) throws  TaskException{
		
		String str = taskServ.deleteTask(id);
		
		return new ResponseEntity<>(str, HttpStatus.OK);
	}
	
}

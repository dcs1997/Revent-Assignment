package com.masai.Services;

import java.util.List;

import com.masai.Exception.TaskException;
import com.masai.Exception.UserException;
import com.masai.Model.Task;

public interface TaskService {
	
   public String addTask(Task task, Integer id)throws UserException;
   
   public List<Task> getAllTasksUsingPagination(Integer id, Integer PageNumber) throws TaskException, UserException;
   
   public Task UpdateTask(Task task, Integer id) throws TaskException;
   
   public String deleteTask(Integer id) throws TaskException;

}

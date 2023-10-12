package com.masai.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masai.Exception.TaskException;
import com.masai.Exception.UserException;
import com.masai.Model.Task;
import com.masai.Model.User;
import com.masai.Repository.TaskRepo;
import com.masai.Repository.UserRepo;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepo tRepo;

	@Autowired
	private UserRepo usrRepo;

	@Override
	public String addTask(Task task, Integer id) throws UserException {

		User us = usrRepo.findById(id).orElseThrow(() -> new UserException("Please enter a valid user id"));

		Task tsk = tRepo.save(task);

		tsk.setUsr(us);

		if (us.getListOfTask() == null) {
			List<Task> li = new ArrayList<>();
			li.add(tsk);
			us.setListOfTask(li);

		}

		else
			us.getListOfTask().add(task);

		usrRepo.save(us);

		return "Task added Successfully!";
	}

	@Override
	public List<Task> getAllTasks(Integer id) throws TaskException, UserException {

		User us = usrRepo.findById(id).orElseThrow(() -> new UserException("Please enter a valid user id"));

		List<Task> tsk = tRepo.findByUsr(us);

		return tsk;

	}

	@Override
	public Task UpdateTask(Task task, Integer id) throws TaskException {

		Task tsk = tRepo.findById(id).orElseThrow(() -> new TaskException("Task not found"));

		tsk.setStatus(task.getStatus());

		tRepo.save(tsk);

		return tsk;
	}

	@Override
	public String deleteTask(Integer id) throws TaskException {

		Task task = tRepo.findById(id).orElseThrow(() -> new TaskException("No data is found"));

		tRepo.delete(task);

		return "Task deleted successfully!!!";
	}

	@Override
	public List<Task> getAllTasksUsingPagination(Integer id, Integer PageNumber) throws TaskException, UserException {

		User us = usrRepo.findById(id).orElseThrow(() -> new UserException("Please enter a valid user id"));

//		List<Task> taskList = tRepo.findByUsr(us);
//		
//		Integer startPoint = (PageNumber) * 3;
//		Integer endPoint = startPoint + 2;
//		
//		List<Task> res = new ArrayList<>();
//		
//		while(startPoint < taskList.size()  && startPoint <= endPoint) {
//			res.add(taskList.get(startPoint++));
//		}
//		
//		return res;
		
		
		 List<Task> taskList = tRepo.findByUsr(us);
		    
		    int tasksPerPage = 3;
		    int startIndex = PageNumber * tasksPerPage;
		    int endIndex = Math.min(startIndex + tasksPerPage, taskList.size());

		    if (startIndex < taskList.size()) {
		        return taskList.subList(startIndex, endIndex);
		    } else {
		        return Collections.emptyList();
		    }

	}

}

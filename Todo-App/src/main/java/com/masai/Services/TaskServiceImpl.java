package com.masai.Services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	private TaskRepo taskRepo;

	@Autowired
	private UserRepo userRepo;

	
	
	
	
	@Override
	public String addTask(Task task, Integer id) throws UserException {

		User us = userRepo.findById(id).orElseThrow(() -> new UserException("Please enter a valid user id"));

		Task tsk = taskRepo.save(task);

		tsk.setUsr(us);

		
		if (us.getListOfTask() == null) {
			List<Task> li = new ArrayList<>();
			li.add(tsk);
			us.setListOfTask(li);
		}
		else  us.getListOfTask().add(task);

		userRepo.save(us);

		return "Task added Successfully!";
	}
	
	
	
	
	@Override
	public Task UpdateTask(Task task, Integer id) throws TaskException {

		Task tsk = taskRepo.findById(id).orElseThrow(() -> new TaskException("Task not found"));

		tsk.setStatus(task.getStatus());

		taskRepo.save(tsk);

		return tsk;
	}

	
	
	
	@Override
	public String deleteTask(Integer id) throws TaskException {

		Task task = taskRepo.findById(id).orElseThrow(() -> new TaskException("No data is found"));

		taskRepo.delete(task);

		return "Task deleted successfully!!!";
	}

	
	
	
	@Override
	public List<Task> getAllTasksUsingPagination(Integer id, Integer PageNumber) throws TaskException, UserException {

		User us = userRepo.findById(id).orElseThrow(() -> new UserException("Please enter a valid user id"));

		List<Task> taskList = taskRepo.findByUsr(us);

		Integer startPoint = (PageNumber) * 3;
		
		Integer endPoint = startPoint + 2;

		List<Task> res = new ArrayList<>();

		while (startPoint < taskList.size() && startPoint <= endPoint) {
			
			res.add(taskList.get(startPoint++));
		}

		return res;
	}

}

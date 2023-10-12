package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.masai.Model.Task;
import com.masai.Model.User;

public interface TaskRepo  extends JpaRepository<Task, Integer>, PagingAndSortingRepository<Task, Integer>{
	
	List<Task> findByUsr(User us);
}

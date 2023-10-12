package com.masai.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.masai.Model.User;


public interface UserRepo  extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer>{
	
	User findByEmail(String email);


}

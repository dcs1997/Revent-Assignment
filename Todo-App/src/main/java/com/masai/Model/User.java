package com.masai.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z]+", message = "Name must not contain numbers or special characters")
	private String name;
	
	@Column(unique = true) 
	@NotNull
    private String email;
	
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Task> listOfTask;

	public User() {
		super();
	}

	public User(int uid, @NotNull String name, String email, String password, List<Task> listOfTask) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.listOfTask = listOfTask;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Task> getListOfTask() {
		return listOfTask;
	}

	public void setListOfTask(List<Task> listOfTask) {
		this.listOfTask = listOfTask;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", email=" + email + ", password=" + password + ", listOfTask="
				+ listOfTask + "]";
	}
	
	
	
	
}

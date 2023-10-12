package com.masai.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Tid;

	private String title;

	private String description;

	private String status;

	@ManyToOne
	@JsonIgnore
	private User usr;

	public Task() {
		super();
	}

	public Task(int tid, String title, String description, String status, User usr) {
		super();
		Tid = tid;
		this.title = title;
		this.description = description;
		this.status = status;
		this.usr = usr;
	}

	public int getTid() {
		return Tid;
	}

	public void setTid(int tid) {
		Tid = tid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

	@Override
	public String toString() {
		return "Task [Tid=" + Tid + ", title=" + title + ", description=" + description + ", status=" + status
				+ "]";
	}

}

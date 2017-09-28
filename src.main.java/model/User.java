package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idUser")
	private Long idUser;
	
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy="userOwner", cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH} )//reveer
	@JsonBackReference
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ToDoTask> listOfToDoTask = new ArrayList<>();
	
	
	public User(){}
	public User(String name, String password){
		this.setName(name);
		this.setPassword(password);
		
		
	}
	
	
	public void addToDoTask(ToDoTask toDoTask) {
		this.getListOfToDoTask().add(toDoTask);
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<ToDoTask> getListOfToDoTask() {
		return listOfToDoTask;
	}
	public void setListOfToDoTask(List<ToDoTask> listOfToDoTask) {
		listOfToDoTask = listOfToDoTask;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	
	
	
	
	
}

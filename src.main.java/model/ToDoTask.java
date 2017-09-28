package model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Proxy(lazy=false)
@Table(name="ToDoTask")
public class ToDoTask {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idToDoTask")
	private Long idToDoTask;
	
	
	@Column(name="title")
	private String title;
	
	@Column(name="dueDate")
	private LocalDate dueDate;
	
	@Column(name="description")
	private String description;
	
	@Column(name="done")
	private Boolean	done;
	
	@JsonManagedReference
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})//reveer
	private User userOwner;
	
	
	
	public ToDoTask(){
		
	}
	
	public ToDoTask(String title, LocalDate dueDate,String description,User userOwner){
		this.setTitle(title);
		this.setDescription(description);
		this.setDone(false);
		this.setDueDate(dueDate);
		this.setUserOwner(userOwner);
		userOwner.addToDoTask(this);
	}
	
	public Boolean editToDoTask(String title, LocalDate dueDate,String description,Boolean done){
		this.setTitle(title);
		this.setDescription(description);
		this.setDone(done);
		this.setDueDate(dueDate);
		
		return true;
		
	}
	
	
	
	
	
	
	
	
	public Long getIdToDoTask() {
		return idToDoTask;
	}

	public void setIdToDoTask(Long idToDoTask) {
		this.idToDoTask = idToDoTask;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public User getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(User userOwner) {
		this.userOwner = userOwner;
	}
	
	
}

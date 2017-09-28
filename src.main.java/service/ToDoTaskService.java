package service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import dao.ToDoTaskDao;
import model.ToDoTask;
import model.User;

public class ToDoTaskService {

	@Autowired
	UserService userService;

	@Autowired
	ToDoTaskDao toDoTaskDAO;
	
	public boolean newToDoTask(ToDoTask newToDoTask, String username) {
		User user=userService.findUserByName(username);
		if(newToDoTask.getDone()==null){
			newToDoTask.setDone(false);
		}
		newToDoTask.setUserOwner(user);
		user.addToDoTask(newToDoTask);
		toDoTaskDAO.persist(newToDoTask);
		userService.update(user);
		return true;
	}

	public boolean deleteToDoTask(ToDoTask deleteToDoTask, String username) {
		ToDoTask toDoTaskToDelete=(ToDoTask) toDoTaskDAO.getEntity(deleteToDoTask.getIdToDoTask());
		if (toDoTaskToDelete.getUserOwner().getName().equals(username)){
			toDoTaskDAO.delete(toDoTaskToDelete.getIdToDoTask());
			return true;
		}else{
			return false;
		}
	}

	public ToDoTask findToDoTaskWithIdOfThisUser(Long idOfToDoTask, String username) {
		ToDoTask toDoTaskToDetails=(ToDoTask) toDoTaskDAO.getEntity(idOfToDoTask);
		if (toDoTaskToDetails.getUserOwner().getName().equals(username)){
			return toDoTaskToDetails;
		}else{
			return null;
		}
		
	}

	public boolean editToDoTask(ToDoTask toDoTask, String username) {
		ToDoTask toDoTaskToEdit=(ToDoTask) toDoTaskDAO.getEntity(toDoTask.getIdToDoTask());
		if (toDoTaskToEdit.getUserOwner().getName().equals(username)){
			toDoTaskToEdit.setTitle(toDoTask.getTitle());
			toDoTaskToEdit.setDescription(toDoTask.getDescription());
			toDoTaskToEdit.setDueDate(toDoTask.getDueDate());
			toDoTaskToEdit.setDone(toDoTask.getDone());
			toDoTaskDAO.update(toDoTaskToEdit);
			return true;
		}else{
			return false;
		}
	}

	public boolean switchToDoTask(ToDoTask toDoTask, String username) {
		ToDoTask toDoTaskToEdit=(ToDoTask) toDoTaskDAO.getEntity(toDoTask.getIdToDoTask());
		if (toDoTaskToEdit.getUserOwner().getName().equals(username)){
			toDoTaskToEdit.setDone(!toDoTaskToEdit.getDone());
			toDoTaskDAO.update(toDoTaskToEdit);
			return true;
		}else{
			return false;
		}
	}

}

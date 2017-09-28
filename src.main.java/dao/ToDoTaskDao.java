package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import model.ToDoTask;
import model.User;

public class ToDoTaskDao implements GenericDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public ToDoTask getEntity(Long id) {
		Session session = sessionFactory.openSession();
		ToDoTask toDoTask = (ToDoTask) session.load(ToDoTask.class, id);
		session.close();
		return toDoTask;
	}
	
	
	
	
	

	public void update(ToDoTask entity) {
		
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(entity);
			tx.commit();

			session.close();
		}


	@Transactional
	@Override
	public void delete(Long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		ToDoTask user = (ToDoTask) session.load(ToDoTask.class, id);
		session.delete(user);
		tx.commit();
		session.close();
		
	}
	
	
	

	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	public void persist(ToDoTask entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		session.close();
	}

	@Override
	public List list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Object entity) {
		// TODO Auto-generated method stub
		
	}

}

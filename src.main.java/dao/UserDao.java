package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import model.User;


public class UserDao implements GenericDao<User> {

	public static String hola="chau";
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public User findByUserName(String username) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT u FROM User as u WHERE u.name=:name");
		query.setParameter("name", username);
	    User user =   (User) query.getSingleResult();
		session.close();
		
		return user;

	}

	public boolean existUserByName(String name) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT u FROM User as u WHERE u.name=:name");
		query.setParameter("name", name);
	    List<User> users = query.getResultList();
		session.close();
		if(users.isEmpty()){
			return false;
		}
		return true;
	}

	@Transactional
	public void persist(User entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		session.close();
		}

	@Override
	public User getEntity(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> list() {
		Session session = sessionFactory.openSession();
		List<User> userList = session.createQuery("from User").getResultList();
		session.close();
		return userList;
	}

	public boolean isAccessData(User user) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT u FROM User as u WHERE u.name=:name AND u.password=:password");
		query.setParameter("name", user.getName());
		query.setParameter("password", user.getPassword());
	    List<User> users =   query.getResultList();
		session.close();
		if(users.isEmpty()){
			return false;
		}
		return true;
	}


	}


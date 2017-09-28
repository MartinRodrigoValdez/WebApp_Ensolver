package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import model.ToDoTask;
import model.User;



public class UserService {

	public static final String SALT= "Ensolver";
	
	@Autowired
	UserDao userDAO;
	
	/*@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
	*/
	/*@Autowired
    private SecurityService securityService;*/
	
	public boolean createNewUser(User user) {
				
		if(this.existUser(user.getName())){
			return false;
		}else{
			this.saveNewUser(user);
			//securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
			return true;
		}
	}
	
	private boolean existUser(String name) {
		return userDAO.existUserByName(name);
	}

	public boolean logInUser(User user){
		
		user.setPassword(this.generateHash(SALT + user.getPassword()));

		
		if(userDAO.isAccessData(user)){
			return true;
		}else{
			return false;
		}
	}
	

	

	public void saveNewUser(User user) {
		
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		// user.getUserRole().add(new UserRole(user,"ROLE_USER"));
		
		//user.setRoles(new HashSet<>(roleRepository.findAll()));
		
		user.setPassword(this.generateHash(SALT + user.getPassword()));
		userDAO.persist(user);
	}
	
	private String generateHash(String input) {
		StringBuilder hash = new StringBuilder();
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}
		return hash.toString();
	}

	public User findUserByName(String username) {
		return userDAO.findByUserName(username);
		
	}

	public void update(User user) {
		userDAO.update(user);
		
	}

	public List<ToDoTask> getListTaskOfUsername(String username) {
		return userDAO.findByUserName(username).getListOfToDoTask();
		
		
		
	}
	
	

	

}

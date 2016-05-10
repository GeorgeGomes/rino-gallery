package auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auth.dao.UserDAO;
import auth.model.User;
import auth.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	public List<User> getList() {
		return userDAO.getList();
	}

	public User findById(Long id) {
		return userDAO.findById(id);
	}
	
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	public void delete(User user) {
		userDAO.delete(user);
	}

	public void saveOrUpdate(User user) {
		userDAO.saveOrUpdate(user);
	}

}
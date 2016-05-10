package auth.service;

import java.util.List;

import auth.model.User;

public interface UserService {
	public List<User> getList();
	public User findById(Long id);
	public User findByUsername(String username);
	public User findByEmail(String email);
	public void delete(User user);
	public void saveOrUpdate(User user);
}

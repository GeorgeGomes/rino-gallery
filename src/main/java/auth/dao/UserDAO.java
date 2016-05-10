package auth.dao;

import java.util.List;

import auth.model.User;

public interface UserDAO {
	public void saveOrUpdate(User user);
	public void delete(User user);
	public User findById(Long id);
	public User findByUsername(String username);
	public User findByEmail(String email);
	public List<User> getList();
}

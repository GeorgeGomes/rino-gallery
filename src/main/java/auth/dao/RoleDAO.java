package auth.dao;

import java.util.List;

import auth.model.Role;

public interface RoleDAO {
	public void saveOrUpdate(Role role); 
	public void delete(Role role);
	public Role findById(Long id); 
	public List<Role> getList();
}
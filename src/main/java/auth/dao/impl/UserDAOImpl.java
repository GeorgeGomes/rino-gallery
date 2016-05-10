package auth.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import auth.dao.UserDAO;
import auth.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	private final SessionFactory sessionFactory;

	@Autowired
	public UserDAOImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getList() {
		List<User> usuarioList = null;
		try {
			usuarioList = sessionFactory.getCurrentSession().createCriteria(User.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioList;
	}

	@Transactional(readOnly = true)
	@Override
	public User findById(Long id) {
		User user = null;
		try {
			user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Transactional(readOnly = true)
	@Override
	public User findByUsername(String username) {
		User user = null;
		try {
			//user = sessionFactory.getCurrentSession().get(User.class, id);
			Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
			query.setParameter("username", username);
			user = (User) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Transactional(readOnly = true)
	@Override
	public User findByEmail(String email) {
		User user = null;
		try {
			//user = sessionFactory.getCurrentSession().get(User.class, id);
			Query query = sessionFactory.getCurrentSession().createQuery("from User where email = :email");
			query.setParameter("email", email);
			user = (User) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Transactional
	@Override
	public void delete(User u) {
		try {
			sessionFactory.getCurrentSession().delete(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Transactional
	@Override
	public void saveOrUpdate(User u) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
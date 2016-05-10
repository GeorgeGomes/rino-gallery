package auth.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import auth.dao.RoleDAO;
import auth.model.Role;

@Repository("autorizacaoDAO")
public class RoleDAOImpl implements RoleDAO {

	private final SessionFactory sessionFactory;

	@Autowired
	public RoleDAOImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getList() {
		List<Role> autorizacaoList = null;
		try {
			autorizacaoList = sessionFactory.getCurrentSession().createCriteria(Role.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autorizacaoList;
	}

	@Transactional(readOnly = true)
	@Override
	public Role findById(Long id) {
		Role autorizacao = null;
		try {
			autorizacao = (Role) sessionFactory.getCurrentSession().get(Role.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autorizacao;
	}

	@Transactional
	@Override
	public void delete(Role a) {
		try {
			sessionFactory.getCurrentSession().delete(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void saveOrUpdate(Role a) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
package ch.cnlab.atm.persistence.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ch.cnlab.atm.domain.User;
import ch.cnlab.atm.persistence.UserDao;


@Repository
public class UserDaoImpl implements UserDao {
	// --------------------------------------
   	// Members
   	// --------------------------------------
	@PersistenceContext
	private EntityManager em;

	// --------------------------------------
   	// Methods
   	// --------------------------------------
	
	@Override
	@SuppressWarnings("unchecked")
	public User loadUserByEmail(String username) {
		List<User> results = em
				.createQuery("select u from User u where u.emailAddress=:emailAddress")
				.setParameter("emailAddress", username).getResultList();
		if (!results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> readAllUsers() {
		return em
				.createQuery("SELECT user FROM User user order by user.firstName, user.lastName, user.address")
				.getResultList();
	}

	@Override
	public void saveUser(User user) {
		em.merge(user);
	}

	@Override
	public User loadUserById(Long id) {
		return em.find(User.class, id);
	}
}

package ch.cnlab.atm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ch.cnlab.atm.domain.User;
import ch.cnlab.atm.persistence.UserDao;
import ch.cnlab.atm.service.UserService;

public class UserServiceImpl implements UserService {
	// --------------------------------------
	// Members
	// --------------------------------------
	private UserDao userDao;

	// --------------------------------------
	// Methods
	// --------------------------------------
	@Override
	@Transactional(readOnly = true)
	public User loadUserByEmail(String name) {
		return userDao.loadUserByEmail(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> listAllUsers() {
		return userDao.readAllUsers();
	}

	@Override
	@Transactional
	public void saveUser(User user) throws Exception {
		if (user == null || !user.isVolatile())
			throw new IllegalArgumentException("Die Validierung ist fehlgeschlagen!");
		userDao.saveUser(user);

	}

	@Override
	@Transactional(readOnly = true)
	public User loadUserById(Long id) {
		return userDao.loadUserById(id);
	}

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}

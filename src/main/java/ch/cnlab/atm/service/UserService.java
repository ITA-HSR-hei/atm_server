package ch.cnlab.atm.service;

import java.util.List;

import ch.cnlab.atm.domain.User;

public interface UserService {

	/**
	 * Returns a List with all the Users, which were created
	 * 
	 * @return list of Users
	 */
	public List<User> listAllUsers();

	/**
	 * Loads user by Email.
	 * 
	 * @param name
	 *            to Load
	 * @return User User with ID
	 */
	public User loadUserByEmail(String name);

	/**
	 * Loads user by ID.
	 * 
	 * @param id
	 *            of the user to load
	 * @return User User with ID
	 */
	public User loadUserById(Long id);

	/**
	 * Saves User to DB.
	 * 
	 * @param user
	 *            User to save
	 * @throws Exception
	 */
	public void saveUser(User user) throws Exception;

}

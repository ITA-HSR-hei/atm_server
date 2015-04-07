package ch.cnlab.atm.persistence;

import java.util.List;

import ch.cnlab.atm.domain.User;

public interface UserDao {

	/**
	 * Loads the User by Email.
	 * 
	 * @param username
	 *            to load
	 * @return User User with ID
	 */
	public User loadUserByEmail(String username);

	/**
	 * Loads the User by ID.
	 * 
	 * @param id
	 *            of the user to load
	 * @return User User with ID
	 */
	public User loadUserById(Long id);

	/**
	 * Reads all Users from Database
	 * 
	 * @return List of saved Users
	 */
	public List<User> readAllUsers();

	/**
	 * Saves the User to DB
	 * 
	 * @param user
	 *            User to save
	 * @throws Exception
	 */
	public void saveUser(User user);
}

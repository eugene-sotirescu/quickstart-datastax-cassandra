package quickstart.jpacassandra.dao;

import java.util.List;
import java.util.UUID;

import quickstart.jpacassandra.entity.User;

public interface UserDao {

	/**
	 * Create a new user.
	 * 
	 * @param int id
	 */
	void create(User newUser);

	/**
	 * Return everything for a user specified by her id.
	 * 
	 * @param int id
	 */
	User read(int id);

	/**
	 * Update user data.
	 * 
	 * @param User
	 *            updated user
	 */
	User update(User user);

	/**
	 * Delete a user from the table.
	 * 
	 * @param int id
	 */
	void delete(int userId);

	/**
	 * Returns the list of all users in the table.
	 * 
	 * @return List users
	 */
	List<User> list();

	/**
	 * Returns the list of all users with the specified last name.
	 * 
	 * @return List users
	 */
	List<User> listByLastName(String lastName);
	
	long getRowCount();

}

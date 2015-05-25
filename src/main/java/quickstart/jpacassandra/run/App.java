package quickstart.jpacassandra.run;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import quickstart.jpacassandra.dao.UserDao;
import quickstart.jpacassandra.dao.UserDaoImpl;
import quickstart.jpacassandra.entity.User;

public class App {
	static User user1;
	static User user2;
	
	static {
	user1 = new User();
	user1.setFirstName("Apollo");
	user1.setLastName("Citharoedus");
	user1.setEmail("apollo@delphi.gr");
	
	user2 = new User();
	user2.setFirstName("Priam");
	user2.setLastName("Basileus");
	user2.setEmail("priam@troy.gr");
	
	}
	public static void main(String[] args) {		
		
		//create a user
		User u = createUser(user1);
		User newUser = getUser(u.getId());
		System.out.println("created new user :" + newUser);
		
		//now let's update this user
		newUser.setEmail("apollo@colonus.gr");
		User updatedUser = updateUser(newUser);
		System.out.println("updated same user:" + updatedUser);
		
		List<User> users = listAll();
		System.out.println("Row count:" + getRowCount());
		System.out.println("All users:" + users);
		
		String lastName = "Citharoedus";
		List<User> usersByLastname = listByLastName(lastName);
		System.out.println("Users called " + lastName + " count:" + usersByLastname.size());
		System.out.println("Users called " + lastName + ":" + usersByLastname);
		
		//delete a random user
		if (users != null && users.size() > 1) {
			Random randomGenerator = new Random();
			int index = randomGenerator.nextInt(users.size());
			int userIdToDelete = users.get(index).getId();
			deleteUser(userIdToDelete);
			getUser(userIdToDelete);
		}
		System.out.println("Row count:" + getRowCount());


	}


	private static long getRowCount() {
		UserDao userDao = new UserDaoImpl();
		return (long) userDao.getRowCount();
		
	}

	private static void deleteUser(int userId) {
		System.out.println("Deleting user with id:" + userId);
		UserDao userDao = new UserDaoImpl();
		userDao.delete(userId);
		
	}


	private static List<User> listByLastName(String lastName) {
		UserDao userDao = new UserDaoImpl();
    	List<User> users = userDao.listByLastName(lastName);
		return users;
	}


	private static User updateUser(User updatedUser) {
		UserDao userDao = new UserDaoImpl();
		userDao.update(updatedUser);
		return updatedUser;
	}


	private static User createUser(User newUser) {
		UserDao userDao = new UserDaoImpl();
		userDao.create(newUser);
		return newUser;
	}


	private static User getUser(int id) {
		UserDao userDao = new UserDaoImpl();
    	User user = userDao.read(id);
    	return user;
	}


	private static List<User> listAll() {
		UserDao userDao = new UserDaoImpl();
    	List<User> users = userDao.list();
		return users;
	}

}

package quickstart.datastax.cassandra.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDaoImpl  {
	private static final Logger log = LoggerFactory
			.getLogger(UserDaoImpl.class);

/*	public void create(User user) {
		if (user.getId() == 0) {
			Random r = new Random(System.currentTimeMillis());
			int randNum = (1 + r.nextInt(2)) * 1000000 + r.nextInt(1000000);
			user.setId(randNum);
		}
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			em.persist(user);
		} finally {
			em.close();
		}
	}

	@Override
	public User read(int id) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			User user = em.find(User.class, id);
			return user;
		} finally {
			em.close();
		}
	}


	@Override
	public User update(User user) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			User oldUser = em.find(User.class, user.getId());
			if (oldUser != null) {
				em.merge(user);
				return oldUser;
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		} finally {
			em.close();
		}
	}


	@Override
	public void delete(int id) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			User user = em.find(User.class, id);
			em.remove(user);
		} finally {
			em.close();
		}
	}

	@Override
	public List<User> list() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u",	User.class);
			List<User> users = query.getResultList();
			return users;
		} finally {
			em.close();
		}
	}

	@Override
	public List<User> listByLastName(String lastName) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> query = cb.createQuery(User.class);
			Root<User> from = query.from(User.class);
			query.select(from.alias("u")).
					where(cb.equal(from.get("lastName"), lastName));
			TypedQuery<User> q = em.createQuery(query);
			List<User> users = q.getResultList();
			return users;
		} finally {
			em.close();
		}
	}
	
	public long getRowCount() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		Query q = em.createQuery ("SELECT count(x) FROM User x");
		 this doesn't work, kundera impl returns a User object instead of a number
		return q.getSingleResult ();
		//inefficient way of getting the count:
		List<User> users = q.getResultList();
		return users.size();
	}*/

}

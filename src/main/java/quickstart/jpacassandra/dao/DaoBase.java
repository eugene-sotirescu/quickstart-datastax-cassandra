package quickstart.jpacassandra.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.impetus.client.cassandra.common.CassandraConstants;
import com.impetus.kundera.PersistenceProperties;

public class DaoBase {
	private static final Logger log = LoggerFactory.getLogger(DaoBase.class);	
	
	final static String PU = "quickstart_pu";

		
	static EntityManagerFactory emf;
	
	synchronized static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			ImmutableMap<String, String> propertyMap =
					   new ImmutableMap.Builder<String, String>()
					       .put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0)
					       .build();
			emf = Persistence.createEntityManagerFactory(PU, propertyMap);
		}
		return emf;
	}
		
}

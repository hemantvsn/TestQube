package com.shasm.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.jolbox.bonecp.BoneCPDataSource;
import com.shasm.model.users.User;


public class DBUtil {

	private static final Map<Integer, EntityManagerFactory> entityManagerFactories = new HashMap<Integer, EntityManagerFactory>();

	
	
	/**
	 * Need Threadsafe implementation
	 * This EntityManager, Datasource, JPA vendor config is sufficient
	 * We no longer need persistence.xml
	 * @param orgId
	 * @return
	 */
	public synchronized static EntityManager initEntityManager(Integer orgId) {
		
		// fallback Approach
		if (orgId == null) {
			orgId = 1;
		}

		EntityManagerFactory factory = entityManagerFactories.get(orgId);

		if (factory == null) {
			LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		    factoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
		    factoryBean.setPackagesToScan("com.shasm.model");
		    factoryBean.setDataSource(createDataSource(orgId));
		    factoryBean.setJpaVendorAdapter(getJPAVendorAdapter());
		    factoryBean.afterPropertiesSet();
		    factory = factoryBean.getNativeEntityManagerFactory();
		    entityManagerFactories.put(orgId, factory);
		}
		return factory.createEntityManager();

	}

	private static BoneCPDataSource createDataSource(int orgId) {
		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setJdbcUrl("jdbc:postgresql://127.0.0.1/testqube" + orgId);
		ds.setUser("hemantvsn");
		ds.setPassword("");
		ds.setDriverClass("org.postgresql.Driver");
		ds.setPartitionCount(3);
		ds.setMinConnectionsPerPartition(5);
		ds.setMaxConnectionsPerPartition(15);
		return ds;

	}
	
	private static HibernateJpaVendorAdapter getJPAVendorAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.POSTGRESQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
		
		return adapter;
	}

	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
		EntityManager em = initEntityManager(1);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		User user = new User();
		user.setEmail("xxx");
		em.persist(user);
		transaction.commit();
		}
			
		System.out.println("---------------------------------------------------------------------------");
	}
}

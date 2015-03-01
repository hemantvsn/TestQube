package com.shasm.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sun.security.auth.module.UnixSystem;

public class DBUtil {
	public synchronized static EntityManager initEntityManager(Integer orgId) {
		if (orgId == null) {
			orgId = 0;
		}

		UnixSystem system = new UnixSystem();
		EntityManagerFactory entityManagerFactory = null;
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url",
				"jdbc:postgresql://127.0.0.1/testqube"
						+ ((orgId > 0) ? orgId : ""));
		properties.put("javax.persistence.jdbc.user", system.getUsername());
		properties.put("javax.persistence.jdbc.password", "");

		entityManagerFactory = Persistence.createEntityManagerFactory("SHASM",
				properties);

		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		return entityManager;
	}
}

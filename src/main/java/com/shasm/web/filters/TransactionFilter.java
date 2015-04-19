package com.shasm.web.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shasm.util.DBUtil;
import com.shasm.web.util.SessionUtil;


public class TransactionFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println(((HttpServletRequest)request).getRequestURL());
		EntityManager entityManager = DBUtil.initEntityManager(1);
		SessionUtil.setEntityManager(entityManager);
		
		entityManager.getTransaction().begin();
		
		
		
		try {
			chain.doFilter(request, response);

			if (SessionUtil.isRequestSuccess()) {
				entityManager.getTransaction().commit();
			}
		} catch (Exception t) {
			LOG.error("SQL Exception in  commit transaction ",t);
		} finally {
			try {
				if (entityManager != null) {
					// In case the last commit fails...
					if (entityManager.getTransaction().isActive()) {
						entityManager.getTransaction().rollback();
					}
				}
			} catch (Exception t) {
				// Unlikely...
				LOG.error("Exception in  rollback ",t);
			}
		}

		SessionUtil.clear();
	}


	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

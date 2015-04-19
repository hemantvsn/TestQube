package com.shasm.web.util;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shasm.model.users.Users;

public class SessionUtil {
	private static final ThreadLocal<EntityManager> entityManager = new ThreadLocal<EntityManager>();
	private static final ThreadLocal<Boolean> requestSuccess = new ThreadLocal<Boolean>();
	private static final ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	private static final ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
	
	/**
	 * Store user in the SESSION SCOPE
	 * @param curUser
	 */
	public static void setUser(Users curUser) {
		if (getRequest() != null) {
			getRequest().getSession(true).setAttribute("user", curUser);
		}
	}
	
	public static void clear(){
		entityManager.remove();
		requestSuccess.remove();
		request.remove();
		response.remove();
	}
	
	
	public static EntityManager getEntitymanager() {
		return entityManager.get();
	}

	public static Boolean isRequestSuccess() {
		return ((requestSuccess.get() == null) || requestSuccess.get());
	}
	
	public static HttpServletRequest getRequest() {
		return request.get();
	}

	public static HttpServletResponse getResponse() {
		return response.get();
	}
	
	public static void setEntityManager(EntityManager entityManager) {
		SessionUtil.entityManager.set(entityManager);
	}

	public static void setRequest(HttpServletRequest request) {
		SessionUtil.request.set(request);
	}

	/**
	 * Setting requestSuccess=false, won't commit the transaction and will rollback all DML
	 */
	public static void setRequestFailed() {
		requestSuccess.set(false);
	}

	public static void setResponse(HttpServletResponse response) {
		SessionUtil.response.set(response);
	}
}

package com.shasm.security;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shasm.model.users.UserPassword;
import com.shasm.model.users.Users;
import com.shasm.util.DBUtil;
import com.shasm.web.util.SessionUtil;
public class CustomAuthenticationProvider implements  AuthenticationProvider{

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String loginUsername = (String) authentication.getPrincipal();
		String loginPassword = (String) authentication.getCredentials();
		
		if (StringUtils.isEmpty(loginUsername))
			throw new UsernameNotFoundException("Username cannot be empty.");
		
		if(StringUtils.isEmpty(loginPassword))
			throw new BadCredentialsException("Password cannot be empty.");
		

		Users user = getUserByUsername(loginUsername);
		UserPassword password = getPassword(user.getId());

		if (user.isBlocked()) {
			throw new BadCredentialsException("User is blocked");
		} else if (!password.matches(loginPassword)) {
			throw new BadCredentialsException("Invalid password.");
		}
		authentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), null);

		SessionUtil.setUser(user);

		return authentication;
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	private Users getUserByUsername(String username) {
		EntityManager em = SessionUtil.getEntitymanager();

		try {
			return em.createQuery("SELECT u FROM Users u WHERE u.email = ?1", Users.class).setParameter(1, username).getSingleResult();
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found");
		}
	}
	
	private UserPassword getPassword(int userId) {
		EntityManager em = SessionUtil.getEntitymanager();
		UserPassword password = em.find(UserPassword.class, userId);
		if (password == null) {
			throw new BadCredentialsException("User not found. Invalid User Id:" + userId);
		}
		return password;
	}
	
	public static void main(String[] args) {
		EntityManager em = DBUtil.initEntityManager(1);
		Users u = new Users();
		u.setEmail("hemantvsn@gmail.com");
		u.setCellNumber(9970290766L);
		u.setFirstName("Hemant");
		u.setLastName("Nagpure");
		
		UserPassword up = new UserPassword("mh12hk8967");
		up.setUser(u);
		up.setPassword("xyzzyhvn");
		
		em.getTransaction().begin();
		
		em.persist(up);
		
		em.getTransaction().commit();
		
		
	}
}

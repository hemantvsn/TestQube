package com.shasm.services;

import javax.persistence.EntityManager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shasm.web.util.SessionUtil;

@Service(value="UserService")
public class UserServiceImpl implements UserService {

	private EntityManager getEntityManager(){
		return SessionUtil.getEntitymanager();
	}
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return null;
	}

}

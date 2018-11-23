package com.isoftstone.sample.jwt;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isoftstone.sample.mapper.UserMapper;
import com.isoftstone.sample.model.User;

/**
 * User Validate
 *
 * @author hackyo Created on 2017/12/8 9:18.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userMapper.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
		} else {
			return JwtUserFactory.create(user);
		}
	}

}
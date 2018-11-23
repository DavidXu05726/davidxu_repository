package com.isoftstone.sample.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isoftstone.sample.common.AbstractService;
import com.isoftstone.sample.common.ServiceException;
import com.isoftstone.sample.constant.Constants;
import com.isoftstone.sample.mapper.UserMapper;
import com.isoftstone.sample.model.Role;
import com.isoftstone.sample.model.User;
import com.isoftstone.sample.service.UserService;
import com.isoftstone.sample.utils.ValidateUtil;

/**
 * Created by MybatisCodeGenerator on 2018/10/26.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	@Transactional
	public void initUser(User user) throws ServiceException {
		if (StringUtils.isEmpty(user.getEmail())) {
			throw new ServiceException("Email cannot be empty.");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new ServiceException("Password cannot be empty.");
		}
		if (!ValidateUtil.isEmail(user.getEmail())) {
			throw new ServiceException("Email format is error.");
		}
		User exist = userMapper.findByEmail(user.getEmail());
		if (exist != null) {
			throw new ServiceException("Email is existed.");
		}
		Date date = new Date();
		user.setPassword(encry(user.getPassword()));
		user.setLastAccessTime(date);
		user.setCreatedTime(date);
		userMapper.insertSelective(user);

		List<Role> roles = user.getRoles();
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		if (roles.size() == 0) {
			roles.add(getDefaultRole());
		}
		Map<String, Object> roleMap = new HashMap<>();
		roleMap.put("userId", user.getId());
		roleMap.put("roles", roles);
		userMapper.relateRole(roleMap);

	}

	private String encry(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	private Role getDefaultRole() {
		Role defaultRole = new Role();
		defaultRole.setCode(Constants.ROLE_DEFAULT);
		return defaultRole;
	}

}

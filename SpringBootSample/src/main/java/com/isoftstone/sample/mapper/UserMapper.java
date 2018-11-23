package com.isoftstone.sample.mapper;

import java.util.Map;

import com.isoftstone.sample.common.Mapper;
import com.isoftstone.sample.model.User;

public interface UserMapper extends Mapper<User> {
	
	User findByEmail(String email);
	
	void relateRole(Map<String, Object> params);
}
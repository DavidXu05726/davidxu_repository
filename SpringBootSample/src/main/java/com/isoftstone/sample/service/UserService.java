package com.isoftstone.sample.service;
import com.isoftstone.sample.common.Service;
import com.isoftstone.sample.common.ServiceException;
import com.isoftstone.sample.model.User;


/**
 * Created by MybatisCodeGenerator on 2018/10/26.
 */
public interface UserService extends Service<User> {
	
	public void initUser(User user) throws ServiceException;

}

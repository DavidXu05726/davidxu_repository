package com.isoftstone.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.sample.common.Result;
import com.isoftstone.sample.common.ResultGenerator;
import com.isoftstone.sample.jwt.JwtUser;
import com.isoftstone.sample.jwt.TokenProvider;
import com.isoftstone.sample.model.User;
import com.isoftstone.sample.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by MybatisCodeGenerator on 2018/10/26.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public Result login(@RequestBody User loginUser) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginUser.getEmail(), loginUser.getPassword());
		Authentication authentication = null;
		try {
			authentication = this.authenticationManager.authenticate(authenticationToken);
		} catch (Exception e) {
			log.error("Login error", e);
			return ResultGenerator.genFailResult("Email or password is error.");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		JwtUser cUser = (JwtUser) userDetailsService.loadUserByUsername(loginUser.getEmail());
		String jwtToken = tokenProvider.generateToken(cUser);
		Map<String, Object> result = new HashMap<>();
		result.put("token", jwtToken);
		result.put("activeUser", cUser);
		return ResultGenerator.genSuccessResult(result).setMessage("Signin successfully.");
	}

	@PostMapping("/signup")
	public Result signup(@RequestBody User newUser) {
		try {
			userService.initUser(newUser);
		} catch (Exception e) {
			log.error("Sign up error", e);
			return ResultGenerator.genFailResult(e.getMessage());
		}
		return ResultGenerator.genSuccessResult(newUser).setMessage("Register successfully.");
	}

	@PostMapping("/delete")
	public Result delete(@RequestParam Integer id) {
		userService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/update")
	public Result update(User user) {
		userService.update(user);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/detail")
	public Result detail(@RequestParam Integer id) {
		User user = userService.findById(id);
		return ResultGenerator.genSuccessResult(user);
	}

	@PostMapping("/list")
	public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<User> list = userService.findAll();
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}

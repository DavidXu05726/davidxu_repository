package com.isoftstone.sample.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Table(name = "t_user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;

	private String password;

	private String phone;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "head_pic_large")
	private String headPicLarge;

	@Column(name = "head_pic_small")
	private String headPicSmall;

	@Column(name = "module_id")
	private Integer moduleId;

	@Column(name = "last_access_time")
	private Date lastAccessTime;
	
	@Column(name = "created_time")
	private Date createdTime;

	@Transient
	private List<Role> roles;

}
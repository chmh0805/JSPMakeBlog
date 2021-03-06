package com.cos.blog.service;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserDao;
import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;
import com.cos.blog.domain.user.dto.UpdateReqDto;

public class UserService {
	
	private UserDao userDao;
	// 회원가입, 회원수정, 로그인, 아이디중복체크
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public int 회원가입(JoinReqDto dto) {
		int result = userDao.save(dto);
		return result;
	}
	
	public User 로그인(LoginReqDto dto) {
		return userDao.findByUsernameAndPassword(dto);
	}
	
	public int 회원수정(int userId, UpdateReqDto dto) {
		return userDao.update(userId, dto);
	}
	
	public int 아이디중복체크(String username) {
		int result = userDao.usernameCheck(username);
		return result;
	}
}

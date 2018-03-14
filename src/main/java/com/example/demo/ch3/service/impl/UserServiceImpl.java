package com.example.demo.ch3.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ch3.entity.User;
import com.example.demo.ch3.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	public User getUser() {
		return null;
	}
	@Transactional
	public void updateUser() {
		System.out.println("事务开始。。。");
		String a=null;
		if(a.length()==1){
			System.out.println(a.toString());
		}
		System.out.println("结束事务。。。");
	}
	
}

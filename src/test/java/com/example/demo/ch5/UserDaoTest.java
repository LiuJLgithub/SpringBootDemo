package com.example.demo.ch5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ch5.dao.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional//增加这个注解使得数据库具有事务回滚功能
public class UserDaoTest {
	@Autowired
	UserDao userDao;
	@Test
	public void testUserDao(){
		System.out.println("user的个数为："+userDao.queryUserNum1()
		);
	}
	@Test
	public void testGetUserMap(){
		System.out.println("userMap为:"+userDao.getUserMap());
	}
	@Test
	public void testUpdateUser(){
		userDao.updateUser2();
		System.out.println("修改成功");
	}
}

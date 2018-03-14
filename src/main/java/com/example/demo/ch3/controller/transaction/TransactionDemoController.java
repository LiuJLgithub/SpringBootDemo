package com.example.demo.ch3.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 测试事务功能
 * @author kdgc_ljl
 *
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.ch3.service.impl.UserServiceImpl;
@Controller
public class TransactionDemoController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@GetMapping("/transaction/updateUser")
	@ResponseBody
	public String UpdateUser(){
		userServiceImpl.updateUser();
		return "update success!!!";
	}
}

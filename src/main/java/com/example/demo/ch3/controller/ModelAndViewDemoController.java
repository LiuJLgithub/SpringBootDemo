package com.example.demo.ch3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ch3.entity.User;

/**
 * 关于使用model、ModelAndView、view的demo
 * @author Liu
 *
 */
@Controller
@RequestMapping(path="/model")
public class ModelAndViewDemoController {
	/*
	 * model会自动封装数据到返回的视图demo
	 */
	@GetMapping(path="/{age}/get1.html")
	public  String getUser(@PathVariable String age,Model model) {
		System.out.println("age:"+age);
		User user=new User();
		user.setAge(age);
		user.setName("张三");
		user.setSex("男");
		model.addAttribute("user1", user);
		return "/admin/userInfo1.btl";
//		return "/index.html";
	}
	/*
	 * 使用ModelAndView封装数据并返回视图demo
	 */
	@GetMapping(path="/{age}/get2.html")
	public ModelAndView getUser2(@PathVariable String age,ModelAndView modelAndView){
		System.out.println("age:"+age);
		User user=new User();
		user.setAge(age);
		user.setName("李四");
		user.setSex("女");
		modelAndView.addObject("user2",user);
		modelAndView.setViewName("/admin/userInfo2.btl");
		return modelAndView;
	}
}

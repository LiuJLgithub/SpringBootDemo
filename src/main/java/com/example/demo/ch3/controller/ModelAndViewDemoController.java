package com.example.demo.ch3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ch3.entity.User;

/**
 * 关于使用model、ModelAndView、view的demo
 * “@ModelAttribute”通常在Controller的某个方法上，此方法首先被调用，并将此方法作为model的属性，然后再调用对应的处理方法。
 * 注：这个只对Model对象有用，对ModelAndView对象无用
 * @author Liu
 *
 */
@Controller
@RequestMapping(path="/model")
public class ModelAndViewDemoController {
	/*
	 * 1、model会自动封装数据到返回的视图demo
	 */
	@GetMapping(path="/{name}/{age}/get1.html")
	public  String getUser1(@PathVariable String age,@PathVariable String name,Model model) {
		System.out.println("model是否包含user："+model.containsAttribute("user"));
		User user=new User();
		user.setAge(age);
		user.setName(name+"1");
		user.setSex("男");
		model.addAttribute("user1", user);
		return "/admin/userInfo1.btl";
//		return "/index.html";
	}
	/*
	 * 使用ModelAndView封装数据并返回视图demo
	 */
	@GetMapping(path="/{name}/{age}/get2.html")
	public ModelAndView getUser2(@PathVariable String age,@PathVariable String name,ModelAndView modelAndView){
		System.out.println("modelAndView是否为空"+modelAndView.isEmpty());
		User user=new User();
		user.setAge(age);
		user.setName(name+"2");
		user.setSex("女");
		modelAndView.addObject("user2",user);
		modelAndView.setViewName("/admin/userInfo2.btl");
		return modelAndView;
	}
	/*
	 *@ModelAttribute的使用
	 */
	@ModelAttribute
	public void getUser(@PathVariable String age,@PathVariable String name,Model model){
		User user=new User();
		user.setAge(age);
		user.setName(name);
		user.setSex("未知");
		System.out.println("@ModelAttribute工作");
		model.addAttribute("user",user);
	}
	@GetMapping(path="/{name}/{age}/getUser.json")
	@ResponseBody
	public String getUserByNameAndAge(Model model){
		System.out.println(model.containsAttribute("user"));
		return "success!!!";
	}
}

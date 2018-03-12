package com.example.demo.ch3.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.ch3.entity.User;
/**
 * 使用用户登录验证拦截器设置是否可用；验证跨域访问设置是否可行
 * @author kdgc_ljl
 *
 */
@Controller
public class UserLoginDemoController {

	/*
	 * 进行用户登录。
	 */
	@PostMapping(path = "/login.do", params = "action=login") // 只支持post请求，同时请求参数中action的值为update。
	public void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("进入userLogin()方法");
		// 这里不进行登录验证了
		User user = new User();
		user.setAge("1");
		user.setName("loginTest");
		user.setSex("未知");
		request.getSession().setAttribute("user", user);
		response.sendRedirect("/interceptDemo/show.html");
	}

	/*
	 * 用户登录后看到的界面
	 */
	@ResponseBody
	@GetMapping(path = "/interceptDemo/show.html")
	public String showUserMessages(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return user.toString();
	}

	/*
	 * 用户登出
	 */
	@ResponseBody
	@GetMapping(path = "/logout.do")
	public String userLogout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "用户已退出登录～";
	}
	/*
	 * 验证跨域访问设置，只可以访问/api/**这层目录下的文件
	 */
//	@ResponseBody
//	@RequestMapping(path="/api/1")
//	public String askCorsMapping(){
//		return "/api/1";
//	}
	/*
	 * 验证配置文件中设置格式化是否有效
	 */
	@ResponseBody
	@RequestMapping(path="/getDate")
	public String getDate(Date d){
		System.out.println(d);
		return d.toString();
	}
}

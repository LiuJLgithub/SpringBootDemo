package com.example.demo.ch3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ch3.entity.User;

/**
 * 使用@RequestBody指定请求发送过来的格式为"application/json"
 * ---验证过程使用curl来进行测试：
 * curl -XPOST http://127.0.0.1:8080/javabean/savejsonorder.json -H 'Content-Type:application/json' -d'{"name":"hello","age":"26"}'
 * @author Liu
 *
 */
@Controller
@RequestMapping(path="/javabean")
public class RequestBodyDemoController {
	@ResponseBody
	@PostMapping(path="/savejsonorder.json")
	public String saveOrderByJson(@RequestBody User user){
		System.out.println("user:"+user.toString());
		return user.getName();
	}
}

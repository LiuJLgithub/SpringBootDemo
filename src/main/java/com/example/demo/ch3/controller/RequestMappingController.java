package com.example.demo.ch3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ch3.entity.User;
/**
 * consumes属性指定了：http请求的Content-type必须与consumes相同。
 * "application/json"对应的是ajax发送的请求，"text/html"对应的是网页接受格式。
 * @author Liu
 *
 */
@RestController
public class RequestMappingController {
@GetMapping(value="/consumes/getUser.json",consumes="application/json")//该请求通过网页访问会报错，因为类型不支持，报415错误-媒体类型不支持
	public User forJson(){
		return new User();
	}
}

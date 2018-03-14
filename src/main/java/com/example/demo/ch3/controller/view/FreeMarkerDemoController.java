package com.example.demo.ch3.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ch3.entity.User;

@Controller
public class FreeMarkerDemoController {
//	@GetMapping("/freeMarker/showUser.html")
//	public ModelAndView showUser(ModelAndView modelAndView){
//		User user=new User("zhangsan","12","男");
//		modelAndView.addObject("user",user);
//		modelAndView.setViewName("/freeMarker/userInfo.ftl");
//		return modelAndView;
//	}
	@GetMapping(path="/freeMarker/index.html")
	public String getIndex(){
		return "/freeMarker/index";
	}
}

package com.example.demo.ch1;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * spring boot中的rest风格代码的编写方式
 * @author Liu
 *
 */
@RestController
public class UserReditRestController {
	@RequestMapping(value="/usercredit/{id}")
	public Integer getCreditLevel(@PathVariable String id){
		return Integer.parseInt(id)+1;
	}
	
}
 
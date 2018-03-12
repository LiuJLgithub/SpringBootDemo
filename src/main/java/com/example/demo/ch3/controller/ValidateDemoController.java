package com.example.demo.ch3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.ch3.entity.WorkInfoForm;

/**
 * Spring支持JSR-303和Bean验证:对对象属性进行验证，可以弥补前端验证的不足
 * 
 * @author kdgc_ljl
 *
 */
@Controller
@RequestMapping(path="/validate")
public class ValidateDemoController {
	/*
	 * 这是测试@Validate的demo
	 */
	@ResponseBody
	@RequestMapping("/addworkinginfo.html")
	public String addWorkInfo(@Validated({ WorkInfoForm.Update.class }) WorkInfoForm workInfoForm, BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError objectError : list) {
				System.out.println(objectError.getObjectName() + "," + ((FieldError) objectError).getField() + "," + objectError.getDefaultMessage());
			}
			return "error";
		}
		return "succ";
	}
}

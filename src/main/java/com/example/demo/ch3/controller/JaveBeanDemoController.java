package com.example.demo.ch3.controller;

import java.security.KeyStore.TrustedCertificateEntry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.ch3.entity.Order;
import com.example.demo.ch3.entity.OrderDetail;
import com.example.demo.ch3.entity.OrderPostForm;
import com.example.demo.ch3.entity.User;
/**
 * 使用JavaBean接收参数
 *备注：curl的windows版本的下载地址为：
 *https://dl.uxnr.de/build/curl/curl_winssl_msys2_mingw64_stc/curl-7.58.0/curl-7.58.0.zip
 *
 *如果出现乱码需要安装iconv，参考：http://blog.csdn.net/thc1987/article/details/52583789
 * @author kdgc_ljl
 *
 */
@Controller
@RequestMapping(value="/javaBean")
public class JaveBeanDemoController {
	@GetMapping(path="/getUser")
	@ResponseBody
	public String getUser(String name,Integer age){

		return "姓名："+name+"，年龄："+age;
	}
	/*
	 * RequestParam使用demo，属性name表示参数名称，属性required表示参数是否必备，参数defaultValue表示参数的默认值。
	 */
	@GetMapping(path="/getUser2")
	@ResponseBody
	public String getUser2(@RequestParam(name="name",required=true,defaultValue="user")String name,Integer age){
		
		return "姓名："+name+"，年龄："+age;
	}
	/*
	 * 可将HTTP的参数转换为JavaBean对象，HTTP参数的名字对应到POJO的属性名，但是在这种情况下若要使用@RequestParam来约束参数，
	 * 则需要将参数单独指定出来
	 */
	@GetMapping(path="/getUser3")
	@ResponseBody
	public String getUser3(@RequestParam(name="name",required=true)String name,User user){
		return user.getName()+","+user.getAge()+","+user.getSex();
		
	}
	/*
	 * HTTP提交的数据有多个name，需要注意：POJO有set方法
	 * 参考：/admin/userInfo.btl中的form。
	 */
	@PostMapping(path="/saveOrder.json")
	@ResponseBody
	public String saveOrder(OrderPostForm orderPostForm){
		System.out.println("订单名称为："+orderPostForm.getOrder().getName());
		for (OrderDetail detail : orderPostForm.getDetails()) {
			System.out.println("订单详情:"+detail.getName());
		}
		return "saveOrder success!!!";
	}
}

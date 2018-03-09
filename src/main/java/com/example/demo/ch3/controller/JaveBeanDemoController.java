package com.example.demo.ch3.controller;

import java.security.KeyStore.TrustedCertificateEntry;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.ch3.entity.Order;
import com.example.demo.ch3.entity.OrderDetail;
import com.example.demo.ch3.entity.OrderPostForm;
import com.example.demo.ch3.entity.User;

/**
 * 1、 使用JavaBean接收参数 
 * 2、使用@InitBinder来绑定属性 
 * 
 * 备注：curl的windows版本的下载地址为：
 * https://dl.uxnr.de/build/curl/curl_winssl_msys2_mingw64_stc/curl-7.58.0/curl-7.58.0.zip
 *( 如果出现乱码需要安装iconv，参考：http://blog.csdn.net/thc1987/article/details/52583789)
 * 
 * @author kdgc_ljl
 *关于@InitBinder和@ModelAttribute的一点使用总结：
 *1、如果@InitBinder没有参数，那么每次请求方法中有对象参数的个数就是@InitBinder对应方法执行的次数；
 *2、如果@InitBinder有参数，那么只用使用@ModelAttribute("参数")来给请求方法对象参数赋值时候才会调用对应的有参的@InitBinder方法
 */
@Controller
@RequestMapping(value = "/javaBean")
public class JaveBeanDemoController {
	@GetMapping(path = "/getUser")
	@ResponseBody
	public String getUser(String name, Integer age) {

		return "姓名：" + name + "，年龄：" + age;
	}

	/*
	 * RequestParam使用demo，属性name表示参数名称，属性required表示参数是否必备，
	 * 参数defaultValue表示参数的默认值。
	 */
	@GetMapping(path = "/getUser2")
	@ResponseBody
	public String getUser2(@RequestParam(name = "name", required = true, defaultValue = "user") String name,
			Integer age) {

		return "姓名：" + name + "，年龄：" + age;
	}

	/*
	 * 可将HTTP的参数转换为JavaBean对象，HTTP参数的名字对应到POJO的属性名，但是在这种情况下若要使用@
	 * RequestParam来约束参数， 则需要将参数单独指定出来
	 */
	@GetMapping(path = "/getUser3")
	@ResponseBody
	public String getUser3(@RequestParam(name = "name", required = true) String name, User user) {
		return user.getName() + "," + user.getAge() + "," + user.getSex();

	}

	/*
	 * HTTP提交的数据有多个name，需要注意：POJO有set方法 参考：/admin/userInfo.btl中的form。
	 */
	@PostMapping(path = "/saveOrder.json")
	@ResponseBody
	public String saveOrder(OrderPostForm orderPostForm) {
		System.out.println("订单名称为：" + orderPostForm.getOrder().getName());
		for (OrderDetail detail : orderPostForm.getDetails()) {
			System.out.println("订单详情:" + detail.getName());
		}
		return "saveOrder success!!!";
	}

	/*
	 * @InitBinder应用场景：1、格式转化，例如String格式的时间转化为Date格式
	 */
	@InitBinder
	protected void initBinderDate(WebDataBinder binder) {
		System.out.println("进入initBinderDate方法");
		binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));// 可以识别这种格式的Date对象
	}

	@ResponseBody
	@RequestMapping(path = "/date")
	public void printDate(Date d) {
		System.out.println(d);
		return;
	}

	/*
	 * @InitBinder应用场景：2、具有相同属性对象的自动绑定问题
	 */
	@InitBinder("order")
	public void initBinderOrder(WebDataBinder binder) {
		System.out.println("进入initBinderOrder方法");
		binder.setFieldDefaultPrefix("order.");
	}

	@InitBinder("detail")
	public void initBinderOrderDetail(WebDataBinder binder) {
		System.out.println("进入initBinderOrderDetail方法");
		binder.setFieldDefaultPrefix("detail.");
	}

	@ResponseBody
	@PostMapping(path = "/data/sameParam")
	public String setDatasWithSameParams(@ModelAttribute("order") Order order,
			@ModelAttribute("detail") OrderDetail detail) {
		System.out.println(order);
		System.out.println(detail);
		return "succ!!";
	}
}

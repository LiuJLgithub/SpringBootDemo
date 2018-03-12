package com.example.demo.ch3.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.ch3.entity.User;

/**
 * WebMvcConfigurer是用来全局化定制SpringBoot的MVC特征。
 * 开发者通过实现WebMvcConfigurer接口来配置MVC全局特征。
 * 总结：
 * 关于spring mvc中的格式转换，有两种方式可以实现：1、使用@initBinder来对格式转换进行绑定；2、在配置类（实现WebMvcConfig）中
 * 在addFormatters方法中指定需要格式转换的类型。
 * 
 * @author kdgc_ljl
 *
 */
@Configuration
public class MvcConfigurerDemoController implements WebMvcConfigurer {

	// 1、跨域访问配置
	public void addCorsMappings(CorsRegistry registry) {
		//仅仅允许来自domain2的跨域访问，并且限定访问路径为/api、方法是POST或者GET
		registry.addMapping("/api/**").allowedOrigins("http://domain2.com").allowedMethods("POST","GET");
	}

	// 2、格式化
	public void addFormatters(FormatterRegistry registry) {
		/*
		 * 2.1 增加关于日期格式转换
		 */
		registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
	}

	// 3、拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		// 增加一个拦截器，检查会话，URL以admin开头的都使用此拦截器
		registry.addInterceptor(new SessionHandlerIntercepter()).addPathPatterns("/interceptDemo/**");
	}
/*
 * 3.1 自定义一个拦截器类
 */
	class SessionHandlerIntercepter implements HandlerInterceptor {

		@Override
		//页面渲染完毕后调用此方法，通常用来清除某些资源，类似Java语法的finally
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
				Exception ex) throws Exception {
			// TODO Auto-generated method stub
			HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
		}

		@Override
		//controller方法处理完毕后调用此方法
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			// TODO Auto-generated method stub
			HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		}

		@Override
		//controller方法处理之前调用此方法
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				// 没有登录，重定向到登录界面
				response.sendRedirect("/login.html");
				System.out.println("用户没有登录，请重新登录～");
				return false;
			}
			System.out.println("用户已登录～");
			return true;
		}

	}

	// 4、URI到视图的映射：应用有的时候没有必要为一个URL指定一个Controller方法，那么可以使用下面的方法-使用ViewControllerRegistry进行注册
	public void addViewControllers(ViewControllerRegistry registry) {
		//例：增加一个view,对于"/viewControllerDemo.html"请求，设置返回视图"/viewControllerDemo.btl"。
		registry.addViewController("/viewControllerDemo.html").setViewName("/viewControllerDemo.btl");
		//例：增加一个重定向,对于重定向的所有"/**/*.rvc"请求，重定向到"index.html"请求。
		registry.addRedirectViewController("/**/*.rvc", "/index.html");
	}
	// 其他更多全局定制接口
	// ...
}

package com.example.demo.ch3.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
/**
 * 使用组验证
 * @author kdgc_ljl
 *
 */
public class WorkInfoForm {
	// 定义一个类，更新时校验组
	public interface Update {
	}

	// 定义一个类，添加时校验组
	public interface Add {
	}

	@NotNull(groups = { Update.class })
	@Null(groups = { Add.class })
	Long id;
	@NotNull(groups = { Update.class })
	@Null(groups = { Add.class })
	String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

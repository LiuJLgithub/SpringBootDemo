package com.example.demo.ch3.entity;

public class Order {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Order [name=" + name + "]";
	}
	
}

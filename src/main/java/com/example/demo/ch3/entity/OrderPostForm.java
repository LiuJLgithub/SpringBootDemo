package com.example.demo.ch3.entity;

import java.util.List;

public class OrderPostForm {
	private  Order order;
	private List<OrderDetail> details;
	public Order getOrder() {
		return order;
	}
	public List<OrderDetail> getDetails() {
		return details;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}
	
}

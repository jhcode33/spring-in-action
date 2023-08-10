package com.jhcode.spring.repository;

import com.jhcode.spring.Order;

public interface OrderRepository {
	Order save(Order order);
}
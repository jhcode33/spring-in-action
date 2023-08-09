package com.jhcode.spring.repository;

import com.jhcode.spring.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}

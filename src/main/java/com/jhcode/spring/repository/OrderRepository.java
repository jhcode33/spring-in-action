package com.jhcode.spring.repository;

import com.jhcode.spring.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
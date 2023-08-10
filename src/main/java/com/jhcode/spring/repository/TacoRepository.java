package com.jhcode.spring.repository;

import com.jhcode.spring.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
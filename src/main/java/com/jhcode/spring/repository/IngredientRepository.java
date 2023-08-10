package com.jhcode.spring.repository;

import com.jhcode.spring.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
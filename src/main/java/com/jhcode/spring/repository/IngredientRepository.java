package com.jhcode.spring.repository;

import com.jhcode.spring.Ingredient;

public interface IngredientRepository {
	Iterable<Ingredient> findAll();
	Ingredient findById(String id);
	Ingredient save(Ingredient ingredient);
}
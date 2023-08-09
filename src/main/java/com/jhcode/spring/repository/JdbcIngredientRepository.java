package com.jhcode.spring.repository;

import com.jhcode.spring.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }


    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id) {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    // RowMapper 함수형 인터페이스를 구현하지 않았지만, mapRow의 메소드 시그니처(파라미터의 타입 및 개수)가
    // 동일하기 때문에 this::mapRowToIngredient 람다 표현식으로 아래 메소드가 구현한 것처럼 동작함
    private Ingredient mapRowToIngredient(ResultSet rs, int i) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }

//    // 람다식 적용
//    public Iterable<Ingredient> lambda() {
//        return jdbc.query("select id, name, type from Ingredient",
//                (rs, num) -> {
//                return new Ingredient(rs.getString("id"),
//                        rs.getString("name"),
//                        Ingredient.Type.valueOf(rs.getString("type")));
//                });
//    }

//    // 익명 객체
//    public Iterable<Ingredient> anonymous() {
//        return jdbc.query("select id, name, type from Ingredient", new RowMapper<Ingredient>(){
//
//            @Override
//            public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new Ingredient(
//                        rs.getString("id"),
//                        rs.getString("name"),
//                        Ingredient.Type.valueOf(rs.getString("type"))
//                );
//            }
//        });
//    }
}

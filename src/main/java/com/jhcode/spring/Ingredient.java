package com.jhcode.spring;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // getter, setter, equals, hashCode, toString, RequiredArgsConstructor
@RequiredArgsConstructor // final과 @NonNull 만 생성자 파라미터로 정의
public class Ingredient {
    // 식자재
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}

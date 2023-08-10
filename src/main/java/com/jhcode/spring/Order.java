package com.jhcode.spring;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Long id;
    private Date placedAt;

    @NotBlank(message="Name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    // Luhn(룬) 알고리즘 검사, 16자리 신용카드 번호 검증
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    // 필드의 값이 MM/YY의 형식
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    /*  @Digits: 숫자 형식을 검증하는 어노테이션입니다.
        integer=3: 정수 부분의 최대 자리수를 3으로 제한합니다.
        fraction=0: 소수 부분이 없어야 하므로 소수 자리수를 0으로 설정합니다. */
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }
}

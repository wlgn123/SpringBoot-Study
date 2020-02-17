package com.std.boot.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Getter를 자동 생성해준다.
@Getter
/**
 *  final로 선언되어있는 변수들에 대하여 생성자를 생성한다.
 */
@RequiredArgsConstructor
public class HelloResonseDto {

    private final String name;
    private final int amount;

}

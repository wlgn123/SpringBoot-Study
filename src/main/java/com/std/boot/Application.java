package com.std.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 스프링 메인 클래스
// 본 클래스는 최상단에 위치해야함.

// JPA Auditing 활성화
// @EnableJpaAuditing
// SpringbootApplication Annotation으로 인해 스프링 부트 자동설정, 스프링 Bean 읽기, 생성 등 자동 설정 진행.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

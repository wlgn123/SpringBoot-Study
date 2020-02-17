package com.std.boot.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 테스트를 진행할 떄 Junit에 내장된 실행자 외에 다른 실행자 실행.
@RunWith(SpringRunner.class )
// WebMvcTest 선언 시, Controller, ControllerAdvice 테스트 가능
@WebMvcTest( controllers = HelloController.class)
public class HelloControllerTest {

    // 빈을 주입하는 어노테이션
    @Autowired
    // 웹 API를 테스트할 때 사용, 스프링 MVC 테스트의 시작점
    // MockMvc를 통해 HTTP GET, POST 등에 대한 API 테스트를 진행할 수 있음.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // /hello 주소로 get 요청을 합니다.
        mvc.perform(get("/hello"))
                /*
                     mvc.perform의 결과를 검증합니다.
                     HTTP Header의 Status를 검증합니다.
                     200, 404, 500 등의 상태 검증
                     ok 일 경우 200
                */
                .andExpect(status().isOk())
                /*
                     mvc.perform의 결과를 검증합니다.
                     응답 본문의 내용을 검증합니다.
                     반환하는 값이 hello와 일치한지 확인.
                */
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        // param 값은 무조건 String만 사용해야 한다.
        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // $ 를 기준으로 json 필드명을 지정한다.
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}

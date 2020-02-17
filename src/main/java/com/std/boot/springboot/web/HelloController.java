package com.std.boot.springboot.web;

import com.std.boot.springboot.web.dto.HelloResonseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Json을 반환하는 컨트롤러 ( 화면 반환 없음 )
@RestController
public class HelloController {

    // Get 요청 API
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResonseDto helloResonseDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResonseDto(name, amount);
    }
}

package com.fastcampus.ch2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. 원격 프로그램으로 등록
@RestController
public class HomeController { // 원격 프로그램
    // 2. URL과 메서드를 연결
    @RequestMapping("/hello")
    public String main(String[] args) {
        return "hello";
    }
}

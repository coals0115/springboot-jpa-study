package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

// 1. 원격 프로그램으로 등록
@Controller
public class HomeController { // 원격 프로그램
    @RequestMapping("/")
    public String main2() {
        return "index";
    }

    // 2. URL과 메서드를 연결
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("lastName", "Bang");
        model.addAttribute("firstName", "Chaemin");
        model.addAttribute("list", Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee"));
        model.addAttribute("bno", 123);

        return "test";
    }
}

package jpabook2.jpashop2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

//    Logger log = LoggerFactory.getLogger(getClass()); // 이걸 위의 애너테이션으로 대체 가능

    @RequestMapping("/")
    public String home() {
        log.info("home controller");

        return "home";
    }
}

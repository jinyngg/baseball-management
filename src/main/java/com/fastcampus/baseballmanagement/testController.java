package com.fastcampus.baseballmanagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @RequestMapping(name = "hello")
    public void test(int num) {
        System.out.println("hellllooooo");
        System.out.println(num);
    }
}

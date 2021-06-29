package com.example.mockservice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiaoyao62 2021/6/29
 */
@Controller
public class ThymleafController {
    @GetMapping("/su") //http://localhost:8081/su
    public String success(){
        return "/test/success";    //  相当于访问 classpath:/templates/test/success.html
    }
}
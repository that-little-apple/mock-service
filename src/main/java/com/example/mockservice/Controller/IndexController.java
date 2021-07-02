package com.example.mockservice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by linxiang on 2016/7/12.
 */

@Controller
public class IndexController{
    /**
     * 返回首页
     */
    @RequestMapping("/")
    public ModelAndView IndexView() {

        return new ModelAndView("index");
    }


}


package com.sekiro.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author dell
 * @create 2020/1/14 23:11
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name",required=false,defaultValue = " ")String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}

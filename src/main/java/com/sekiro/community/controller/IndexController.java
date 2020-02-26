package com.sekiro.community.controller;

import com.sekiro.community.dto.PageDTO;
import com.sekiro.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author dell
 * @create 2020/1/14 23:11
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue="1")Integer page,
                        @RequestParam(name = "size",defaultValue="5")final Integer  size){
        PageDTO pages=questionService.list(page,size);
        model.addAttribute("pages",pages);
        return "index";
    }

}

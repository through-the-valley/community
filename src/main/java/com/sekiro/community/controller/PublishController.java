package com.sekiro.community.controller;

import com.sekiro.community.mapper.QuestionMapper;
import com.sekiro.community.mapper.UserMapper;
import com.sekiro.community.model.Question;
import com.sekiro.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author dell
 * @create 2020/1/17 15:16
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String tag,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null||title.equals(""))
        {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }if(description==null||description.equals(""))
        {
            model.addAttribute("error","内容不能为空");
            return "publish";
        }if(tag==null||tag.equals(""))
        {
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        Question question=new Question();
        Cookie[] cookies=request.getCookies();
        User user=null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user==null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }else{
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
            return "redirect:/";
        }

    }
}

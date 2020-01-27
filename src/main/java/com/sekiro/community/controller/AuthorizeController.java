package com.sekiro.community.controller;

import com.sekiro.community.dto.AccessTokenDTO;
import com.sekiro.community.dto.GitHubUser;
import com.sekiro.community.mapper.UserMapper;
import com.sekiro.community.model.User;
import com.sekiro.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author dell
 * @create 2020/1/16 11:15
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request,
                            HttpServletResponse response){
        AccessTokenDTO accessTokenDto = new AccessTokenDTO();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setClientId(client_id);
        accessTokenDto.setClientSecret(client_secret);
        accessTokenDto.setRedirectUri(redirect_uri);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if(gitHubUser!=null&&gitHubUser.getId()!=null){

            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            //登陆成功，写cookie和session
            request.getSession().setAttribute("user",gitHubUser);
            return "redirect:/";
        }else{
            //登陆失败，重新登陆
            return "redirect:/";
        }
    }
}

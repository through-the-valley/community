package com.sekiro.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author dell
 * @create 2020/1/16 11:29
 */
//dto是指data transfer object 用来封装多个参数

@Component
@Data
public class AccessTokenDTO {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String state;
}

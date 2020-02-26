package com.sekiro.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author dell
 * @create 2020/2/25 18:51
 */
@Data
@Component
public class TokenDTO {
    private String clientId;
    private String clientSecret;
    private String code;
}

package com.sekiro.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author dell
 * @create 2020/1/16 12:05
 */
@Component
@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}

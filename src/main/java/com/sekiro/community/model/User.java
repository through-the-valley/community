package com.sekiro.community.model;

import lombok.Data;

/**
 * @Author dell
 * @create 2020/1/17 11:06
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long gmtCreate;
    private long gmtModified;
    private String avatarUrl;

}

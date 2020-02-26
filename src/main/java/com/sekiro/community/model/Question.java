package com.sekiro.community.model;

import lombok.Data;

/**
 * @Author dell
 * @create 2020/1/23 20:21
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;


}

package com.sekiro.community.dto;

import com.sekiro.community.model.User;
import lombok.Data;

/**
 * @Author dell
 * @create 2020/1/25 15:41
 */
@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}

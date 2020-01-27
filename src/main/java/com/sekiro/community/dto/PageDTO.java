package com.sekiro.community.dto;

import com.sekiro.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dell
 * @create 2020/1/27 14:40
 */
@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirst;
    private boolean showNext;
    private boolean showLast;
    private Integer current;
    private Integer totalPage;
    //存放当前页数列表里是哪些页
    private List<Integer> showPages=new ArrayList<>();
    public void setPagination(Integer totalCount, Integer page, Integer size) {

        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        if(page<1) page=1;
        if(page>totalPage) page=totalPage;
        current=page;
        showPages.add(page);
        for(int i=1;i<4;i++){
            if(page-i>0){
                showPages.add(0,page-i);
            }
            if(page+i<=totalPage){
                showPages.add(page+i);
            }
        }
        showPrevious= page != 1;
        showNext= !page.equals(totalPage);

        showFirst= !showPages.contains(1);
        showLast= !showPages.contains(totalPage);
    }
}

package com.sekiro.community.service;

import com.sekiro.community.dto.QuestionDTO;
import com.sekiro.community.mapper.QuestionMapper;
import com.sekiro.community.mapper.UserMapper;
import com.sekiro.community.model.Question;
import com.sekiro.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dell
 * @create 2020/1/25 15:41
 */
@Service
public class QuestionService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;


    public List<QuestionDTO> list() {
        List<Question> questions=questionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for(Question question:questions){
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            //快速拷贝对象的属性
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}

package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.domain.Course;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.evaluation.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getAllCourseInfo() {
        return list();
    }

    @Override
    public Course getCourseInfoByName(String name){
        LambdaUpdateWrapper<Course> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Course::getCname,name);
        Course one;
        try{
            one = getOne(wrapper);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误");
        }return one;
    }

    @Override
    public boolean deleteCourde(String cid){
        if(removeById(cid)) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(),"课程不存在");
    }

    @Override
    public List<User> getSCStudent(tid, cid){

    }
}

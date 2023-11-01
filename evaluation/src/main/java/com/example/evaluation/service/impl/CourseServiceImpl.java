package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.domain.Course;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.CourseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.evaluation.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public List<Course> getAllCourseInfo() {
        return list();
    }

    // TODO: 2023-11-01 空值处理
    @Override
    public List<Course> getCourseInfoByTid(String tid) {
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Course::getTid,tid);
        return list(wrapper);
    }

    @Override
    public boolean saveOrUpdateCourseInfo(Course course) {
        LambdaUpdateWrapper<Course> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Course::getCid,course.getCid())
                .set(Course::getCname,course.getCname())
                .set(Course::getTid,course.getTid())
                .set(Course::getContent,course.getContent());

        boolean flag = saveOrUpdate(null,wrapper);
        if(flag) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

//    @Override
//    public boolean deleteCourse(String cid){
//        if(removeById(cid)) return true;
//        else throw new ServiceException(HttpStatus.NOT_FOUND.value(),"课程不存在");
//    }

//    @Override
//    public List<User> getSCStudent(tid, cid){
//
//    }
}

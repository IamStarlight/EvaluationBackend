package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.controller.dto.CourseDto;
import com.example.evaluation.entity.Course;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.evaluation.service.CourseService;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl
        extends ServiceImpl<CourseMapper, Course>
        implements CourseService {

    @Autowired
    private CourseMapper mapper;

    @Override
    public void addNewCourse(CourseDto dto) {
        Course course = new Course();
        course.setTid(dto.getTid());
        course.setCname(dto.getCname());
        course.setContent(dto.getContent());
        if(!save(course)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加课程失败");
        }
    }

    @Override
    public void updateCourseInfo(Course course) {
        if(!updateById(course)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "课程不存在");
        }
    }

    @Override
    public void updateCourseInfo(Integer cid, String content) {
        Course course = new Course();
        course.setCid(cid);
        course.setContent(content);
        if(!updateById(course)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "课程不存在");
        }
    }

    @Override
    public void deleteCourse(Integer cid){
        if(!removeById(cid)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"课程不存在");
        }
    }

    @Override
    public List<Map<String,String>> getCourseListByTid(Integer tid) {
        List<Map<String,String>> list = mapper.getCourseListByTid(tid);
        //老师没课程无需报错，返回空值
//        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }

    @Override
    public List<Map<String,String>> getCourseListBySid(Integer sid) {
        List<Map<String,String>> list = mapper.getCourseListBySid(sid);
        //学生没课程无需报错，返回空值即可
//        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;

    }

    public List<Map<String,String>> getAllCourseInfo() {
        List<Map<String,String>> list = mapper.getAllCourseInfo();
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
        return list;
    }

    @Override
    public List<Map<String, String>> getCourseInfo(Integer cid) {
        List<Map<String, String>> one = mapper.getCourseInfo(cid);
        if(one.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return one;
    }

    @Override
    public Integer getHomeworkNumber(Integer cid) {
        return mapper.getHomeworkNumber(cid);
    }

    @Override
    public void updateHomeworkNumber(Integer cid, Integer wid) {
        if(!mapper.updateHomeworkNumber(cid,wid)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"课程不存在");
        }
    }
}

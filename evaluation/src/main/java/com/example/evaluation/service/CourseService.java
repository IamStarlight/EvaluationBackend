package com.example.evaluation.service;

import com.example.evaluation.controller.dto.CourseDto;
import com.example.evaluation.entity.Course;
import com.example.evaluation.entity.User;

import java.util.List;
import java.util.Map;

public interface CourseService {

    boolean addNewCourse(CourseDto dto);

    boolean updateCourseInfo(Course course);

    boolean updateCourseInfo(Integer cid,String cname,String content);

    boolean deleteCourse(Integer cid);

    List<Map<String,String>> getCourseListByTid(Integer tid);

    List<Map<String,String>> getCourseListBySid(Integer sid);
}

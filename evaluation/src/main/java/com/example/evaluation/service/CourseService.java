package com.example.evaluation.service;

import com.example.evaluation.controller.dto.CourseDto;
import com.example.evaluation.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {

    void addNewCourse(CourseDto dto);

    void updateCourseInfo(Course course);

    void updateCourseInfo(Integer cid,String content);

    void deleteCourse(Integer cid);

    List<Map<String,String>> getCourseListByTid(Integer tid);

    List<Map<String,String>> getCourseListBySid(Integer sid);

    List<Map<String, String>> getCourseInfo(Integer cid);

    Integer getHomeworkNumber(Integer cid);

    void updateHomeworkNumber(Integer cid, Integer wid);
}

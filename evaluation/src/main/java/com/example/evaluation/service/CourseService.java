package com.example.evaluation.service;

import com.example.evaluation.domain.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourseInfo();

    List<Course> getCourseInfoByTid(String tid);

//    List<Course> getCourseInfoBySid(String sid);

    boolean saveOrUpdateCourseInfo(Course course);
}

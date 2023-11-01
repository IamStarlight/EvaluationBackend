package com.example.evaluation.service;

import com.example.evaluation.domain.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourseInfo();

    Course getCourseInfoByName(String name);

    boolean deleteCourse(String cid);

    List<User> getSCStudent(String tid, String cid);
}

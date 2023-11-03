package com.example.evaluation.service;

import com.example.evaluation.entity.Course;
import com.example.evaluation.entity.User;

import java.util.List;
import java.util.Map;

public interface CourseService {

//    List<Course> getCourseInfoByTname(String tname);

    Map<String,String> getCourseInfoByCid(String cid);

    boolean saveOrUpdateCourseInfo(Course course);

    boolean deleteCourse(String cid);

    List<Map<String,String>> getCourseListByTid(String tid);

    List<Map<String,String>> getCourseListBySid(String sid);

    List<User> getAllSCList(String cid);
}

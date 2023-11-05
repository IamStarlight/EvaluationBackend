package com.example.evaluation.service;

import com.example.evaluation.entity.Course;
import com.example.evaluation.entity.User;

import java.util.List;
import java.util.Map;

public interface CourseService {

    Map<String,String> getCourseInfoByCid(Integer cid);

    boolean saveOrUpdateCourseInfo(Course course);

    boolean deleteCourse(Integer cid);

    List<Map<String,String>> getCourseListByTid(Integer tid);

    List<Map<String,String>> getCourseListBySid(Integer sid);

    List<User> getAllSCList(Integer cid);
}

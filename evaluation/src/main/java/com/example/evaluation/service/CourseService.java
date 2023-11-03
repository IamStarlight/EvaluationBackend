package com.example.evaluation.service;

import com.example.evaluation.domain.Course;
import com.example.evaluation.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface CourseService {

//    List<Course> getCourseInfoByTname(String tname);

//    List<Course> getCourseInfoBySid(String sid);

    boolean saveOrUpdateCourseInfo(Course course);

    boolean deleteCourse(String cid);

    List<Map<String,String>> getCourseListByTid(String tid);

    List<Map<String,String>> getCourseListBySid(String sid);
}

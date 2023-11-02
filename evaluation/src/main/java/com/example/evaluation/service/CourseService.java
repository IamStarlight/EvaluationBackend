package com.example.evaluation.service;

import com.example.evaluation.domain.Course;
import com.example.evaluation.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<Course> getAllCourseInfo();

    List<Course> getCourseInfoByTname(String tname);

//    List<Course> getCourseInfoBySid(String sid);

    boolean saveOrUpdateCourseInfo(Course course);

    boolean deleteCourse(String cid);

    List<Course> getCourseListByTid(String tid);

    List<User> getSCListByCid( String cid);

    List<Course> getCourseListBySid(String sid);
}

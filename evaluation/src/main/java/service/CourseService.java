package service;

import domain.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourseInfo();

    Course getCourseInfoByName(String name);
}

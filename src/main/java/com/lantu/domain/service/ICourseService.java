package com.lantu.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantu.domain.entity.Course;
import com.lantu.domain.entity.User;

import java.util.List;

public interface ICourseService extends IService<Course> {


    boolean addCourse(Course user);

    boolean removeCourse(Course course);

    boolean changeCourse(Course course);

    List<Course> getMyCourse(String token);
}

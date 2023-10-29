package com.lantu.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lantu.domain.entity.Course;
import com.lantu.domain.entity.SC;
import com.lantu.domain.entity.User;

import java.util.List;


public interface SCMapper extends BaseMapper<SC> {
    List<Course> getMyCourse(Integer id);

    List<User> getTeacherCourse(Integer id , Integer cid);

    List<User> getAllCourseStudent(Integer cid);
}

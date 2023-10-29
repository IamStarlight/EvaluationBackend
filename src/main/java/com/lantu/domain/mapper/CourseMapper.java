package com.lantu.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lantu.domain.entity.Course;
import com.lantu.domain.entity.User;

public interface CourseMapper extends BaseMapper<Course> {
    Course getMyCourse(String token);
}

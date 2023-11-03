package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.domain.Course;
import com.example.evaluation.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
@Component
public interface CourseMapper extends BaseMapper<Course> {
    List<User> getAllSCList(@Param("cid") String cid);

    List<Map<String,String>> getCourseListByTid(@Param("tid") String tid);

    List<Map<String,String>> getCourseListBySid(@Param("sid") String sid);
}

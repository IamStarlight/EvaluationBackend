package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.entity.Course;
import com.example.evaluation.entity.User;
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

    Map<String,String> getCourseInfoByCid(@Param("cid") Integer cid);

    List<Map<String,String>> getCourseListByTid(@Param("tid") Integer tid);

    List<Map<String,String>> getCourseListBySid(@Param("sid") Integer sid);

    List<Map<String, String>> getAllCourseInfo();

    List<Map<String, String>> getCourseInfo(@Param("cid") Integer cid);
}

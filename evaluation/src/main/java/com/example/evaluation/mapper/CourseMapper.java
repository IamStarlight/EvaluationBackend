package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.domain.Course;
import com.example.evaluation.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@Component
public interface CourseMapper extends BaseMapper<Course> {

//    List<Course> getCourseInfoByTname(@Param("tname") String tname);
//
//    List<Course> getCourseInfoBySname(@Param("sname")String sname);

    List<User> getAllSCList(@Param("cid") String cid);

    List<Course> getCourseListByTid(String tid);

    List<Course> getCourseListBySid(@Param("sid") String sid);
}

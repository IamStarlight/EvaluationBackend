package com.example.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.evaluation.entity.Student;
import com.example.evaluation.entity.User;
import com.example.evaluation.enums.PerEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
@Component
public interface UserMapper extends BaseMapper<User> {

    String getPermsById(@Param("id") Integer id);

    Map<String,String> getOneByID(@Param("id") Integer id);
}

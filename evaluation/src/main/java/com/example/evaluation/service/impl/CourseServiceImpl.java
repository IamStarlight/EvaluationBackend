package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.entity.Course;
import com.example.evaluation.entity.User;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.evaluation.service.CourseService;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl
        extends ServiceImpl<CourseMapper, Course>
        implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<String,String> getCourseInfoByCid(String cid) {
        Map<String,String> one;
        try{
            one = courseMapper.getCourseInfoByCid(cid);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误");
        }return one;
    }

    @Override
    public boolean saveOrUpdateCourseInfo(Course course) {
        LambdaUpdateWrapper<Course> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Course::getCid,course.getCid())
                .set(Course::getCname,course.getCname())
                .set(Course::getTid,course.getTid())
                .set(Course::getContent,course.getContent());

        boolean flag = saveOrUpdate(null,wrapper);
        if(flag) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    public boolean deleteCourse(String cid){
        if(removeById(cid)) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(),"课程不存在");
    }

    @Override
    public List<Map<String,String>> getCourseListByTid(String tid) {
        List<Map<String,String>> list = courseMapper.getCourseListByTid(tid);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }

    @Override
    public List<Map<String,String>> getCourseListBySid(String sid) {
        List<Map<String,String>> list = courseMapper.getCourseListBySid(sid);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;

    }

    @Override
    public List<User> getAllSCList(String cid) {
        List<User> list = courseMapper.getAllSCList(cid);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }
}

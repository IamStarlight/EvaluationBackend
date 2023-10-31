package service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import domain.Course;
import exception.ServiceException;
import mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getAllCourseInfo() {
        List<Course> list = courseMapper.selectAllCourseInfo();
        if(list.isEmpty())
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");

        return list;
    }

    public Course getCourseInfoByName(String name){
        LambdaUpdateWrapper<Course> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Course::getCname,name);
        Course one;
        try{
            one = getOne(wrapper);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误");
        }return one;
    }
}

package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@Component
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> selectAllCourseInfo();
}

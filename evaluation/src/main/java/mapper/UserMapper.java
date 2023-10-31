package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface UserMapper extends BaseMapper<User> {
}

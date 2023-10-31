package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import domain.Submit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface SubmitMapper extends BaseMapper<Submit> {
}

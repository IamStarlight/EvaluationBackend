package com.lantu.domain.service.impl;

import com.lantu.domain.entity.Comments;
import com.lantu.domain.mapper.CommentsMapper;
import com.lantu.domain.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gxccc
 * @since 2023-05-29
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {


    @Override
    public boolean addComments(Comments comments) {
        int a = this.baseMapper.insert(comments);
        if(a==1){
            return true;
        }
        return false;
    }

    @Override
    public List<Comments> searchCommentsbyaid(int id) {
        Comments a = new Comments();
        a.setId(id);
        Map<String,Object> map = new HashMap<>();
        map.put("aid",id);
        List<Comments> comments = this.baseMapper.selectByMap(map);
        return  comments;
    }

    @Override
    public boolean removeComments(Comments comments) {
        if(this.baseMapper.deleteById(comments)!=0){
            return true;
        }
        return false;
    }
}

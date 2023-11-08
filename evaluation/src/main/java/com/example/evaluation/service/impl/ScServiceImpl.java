package com.example.evaluation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.evaluation.entity.SC;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.ScMapper;
import com.example.evaluation.service.ScService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScServiceImpl
        extends MppServiceImpl<ScMapper, SC>
        implements ScService {

    @Autowired
    private ScMapper mapper;

    @Override
    public List<Map<String,String>> getAllSCList(Integer cid) {
        List<Map<String,String>> list = mapper.getAllSCList(cid);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }

    @Override
    public boolean deleteScStu(Integer sid, Integer cid) {
        if(mapper.deleteScStu(sid,cid)) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
    }

    @Override
    public boolean addScStu(Integer cid, Integer sid) {
        if(mapper.addScStu(sid,cid)) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
    }
}

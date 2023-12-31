package com.example.evaluation.service.impl;

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
    public List<Integer> getAllSCListSid(Integer cid) {
        List<Integer> list = mapper.getAllSCListSid(cid);
//        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }

    @Override
    public List<Map<String,String>> getAllSCList(Integer cid) {
        List<Map<String,String>> list = mapper.getAllSCList(cid);
//        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        return list;
    }

    @Override
    public void deleteScStu(Integer sid, Integer cid) {
        if(!mapper.deleteScStu(sid,cid)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
//        create trigger minus_sc_number
//        after delete on sc for each row
//        begin
//        update course
//        set class_number=class_number-1
//        where cid=old.cid;
//        end;
    }

    @Override
    public void addScStu(Integer sid, Integer cid) {
        // TODO: 2023-11-09 没这个人不能加，外键

        if(getOneSCStudent(sid,cid)==null){// 重了报错
            throw new ServiceException(HttpStatus.FOUND.value(),"学生已在选课列表中");
        }
        if(!mapper.addScStu(sid,cid)) {
            throw new ServiceException(HttpStatus.NOT_MODIFIED.value(),"添加学生失败");
        }
//        create trigger sc_number
//        after insert on sc for each row
//        begin
//        update course
//        set class_number=class_number+1
//        where cid=new.cid;
//        end;
    }

    public List<Map<String,String>> getOneSCStudent(Integer sid, Integer cid) {
        List<Map<String,String>> list = mapper.getOneSCStudent(sid,cid);
//        if(list.isEmpty()) {
//            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
//        }
        return list;
    }
}

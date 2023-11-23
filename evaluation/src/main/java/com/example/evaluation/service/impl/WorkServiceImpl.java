package com.example.evaluation.service.impl;

import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.controller.dto.NewHomeworkDto;
import com.example.evaluation.controller.dto.OpenPeerDto;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.entity.StuWork;
import com.example.evaluation.enums.StatusEnum;
import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.CronMapper;
import com.example.evaluation.mapper.WorkMapper;
import com.example.evaluation.utils.CronUtil;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.evaluation.service.WorkService;

import java.util.*;

@Service
@EnableAsync //开启多线程
@EnableScheduling //开启定时任务
public class WorkServiceImpl
        extends MppServiceImpl<WorkMapper, Homework>
        implements WorkService {

    @Autowired
    private WorkMapper mapper;

    @Autowired
    private CronMapper cronMapper;

    @Autowired
    private ScServiceImpl scService;

    @Autowired
    private SubmitServiceImpl submitService;

    @Autowired
    private CourseServiceImpl courseService;

    @Override
    public List<HomeworkInfo> getAllWorkInfoBySid(Integer sid) {
        List<HomeworkInfo> list = mapper.getAllWorkInfoBySid(sid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return list;
    }

    @Override
    public List<HomeworkInfo> getAllWorkInfoByCid(Integer cid) {
        List<HomeworkInfo> list = mapper.getAllWorkInfoByCid(cid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return list;
    }

    @Override
    public List<HomeworkInfo> getWorkInfoById(Integer wid, Integer cid) {
        List<HomeworkInfo> one = mapper.getWorkInfoById(wid,cid);
        if(one.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return one;
    }

    // TODO: 2023-11-10 批量导入选课列表
    @Override
    public List<HomeworkInfo> getStuWorkInfo(Integer sid, Integer cid) {
        List<HomeworkInfo> list = mapper.getStuWorkInfo(sid,cid);
        if(list.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        return list;
    }

    @Override
    public boolean checkOvertime(Integer wid, Integer cid, Date submitTime) {
        Homework idEntity = new Homework();
        idEntity.setWid(wid);
        idEntity.setCid(cid);
        Homework one = selectByMultiId(idEntity);
        if(one == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"该作业不存在");
        }
        return submitTime.compareTo(one.getEndTime()) > 0;
    }


    @Override
    public void createNewWork(NewHomeworkDto h) {
        Integer newNumber = courseService.getHomeworkNumber(h.getCid())+1;
        h.setWid(newNumber);

//        Date startTime = new Date();
//        h.setStartTime(startTime);

        Homework w = new Homework();
        w.setWid(h.getWid());
        w.setCid(h.getCid());
        w.setTitle(h.getTitle());
        w.setDetails(h.getDetails());
        w.setUrl(h.getUrl());
        w.setStatus(String.valueOf(h.getStatus()));
        w.setStartTime(h.getStartTime());
        w.setEndTime(h.getEndTime());

        if(!save(w)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "创建作业失败");
        }
        //创建作业后，作业数+1
        courseService.updateHomeworkNumber(h.getCid(),newNumber);

        //创建作业后，生成空提交
        List<Integer> list = scService.getAllSCListSid(w.getCid());
        for (Integer sid : list) {
            submitService.generateSubmitList(sid,w.getWid(),w.getCid());
        }

        //将时间范围转化为cron
        String startCron = CronUtil.dateToCron(w.getStartTime());
        String endCron = CronUtil.dateToCron(w.getEndTime());

        //插入数据库
        cronMapper.insertStartCron(w.getWid(),w.getCid(),startCron);
        cronMapper.insertEndCron(w.getWid(),w.getCid(),endCron);

    }

    @Override
    public void updateVisible(Integer wid, Integer cid, Integer s) {
        StatusEnum status;
        switch (s){
            case 1:{
                status=StatusEnum.DRAFT;
                break;
            } case 2:{
                status=StatusEnum.RELEASED;
                break;
            } case 3:{
                status=StatusEnum.OVER;
                break;
            } default:{
                throw new ServiceException(HttpStatus.FORBIDDEN.value(),"错误的作业状态码");
            }
        }
        if(!mapper.updateVisible(wid,cid,status)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
    }

    @Override
    public void updateWorkInfo(Homework homework) {
        if(!updateByMultiId(homework)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
    }

    @Override
    public void deleteHomework(Integer wid, Integer cid) {
        Homework idEntity = new Homework();
        idEntity.setWid(wid);
        idEntity.setCid(cid);
        if(!deleteByMultiId(idEntity)) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
        //删除作业的同时删除所有提交
        List<Map<String,Object>> list = submitService.getSubmitListAll(wid,cid);
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> m : map.entrySet()) {
                if("sid".equals(m.getKey())) {
                    submitService.deleteOneSubmitted((Integer) m.getValue(),wid,cid);
                }
            }
        }
        //删除作业后，作业数-1
        //不能-1，不然序号出问题
//        Integer newNumber = courseService.getHomeworkNumber(cid)-1;
//        courseService.updateHomeworkNumber(cid,newNumber);
    }

    @Override
    public List<HomeworkInfo> getAllWorkInfoByTid(Integer id, Integer cid) {
        List<HomeworkInfo> one = mapper.getAllWorkInfoByTid(id,cid);
        if(one.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
        return one;
    }

    @Override
    public void updateOpenPeer(OpenPeerDto d) {
        if(!mapper.updateOpenPeer(d.getWid(),d.getCid(),d.getStatus(),d.getDdl())) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"记录不存在");
        }
    }

    @Override
    @Async
    @Scheduled(cron = "*/2  *  *  *  *  ?")//2秒执行一次
    public void evaStatusToEnd() {
        List<StuWork> list = submitService.getAll();
        Date nowTime = new Date();
        for (StuWork h: list){
            Date endTime = mapper.getEvaDdl(h.getWid(),h.getCid());
            if(nowTime.compareTo(endTime) >= 0
                    && "已发布互评".equals(mapper.getStatus(h.getWid(),h.getCid()))) {
                mapper.evaStatusToEnd(h.getWid(),h.getCid());
            }
        }
    }


    @Override
    public Object getOneWorkInfoBySid(Integer id, Integer wid, Integer cid) {
        List<HomeworkInfo> one = mapper.getOneWorkInfoBySid(id,cid,wid);
        if(one.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
        return one;
    }


    @Override
    public List<HomeworkInfo> getAllDraftWorkInfoByTid(Integer id, Integer cid) {
        List<HomeworkInfo> one = mapper.getAllDraftWorkInfoByTid(id,cid);
        if(one.isEmpty()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }
        return one;
    }

    @Override
    public Integer getSubmitNumber(Integer wid, Integer cid) {
        return mapper.getSubmitNumber(wid,cid);
    }

    @Override
    public void updateSubmitNumber(Integer wid, Integer cid, Integer newNumber) {
        mapper.updateSubmitNumber(wid,cid,newNumber);
    }

    @Override
    @Async
    @Scheduled(cron = "*/2  *  *  *  *  ?")//2秒执行一次
    public void statusToRelease() {
        List<HomeworkInfo> list = mapper.getAll();
        Date nowTime = new Date();
        for (HomeworkInfo h: list){
            if(nowTime.compareTo(h.getStartTime()) >= 0
                    && nowTime.compareTo(h.getEndTime()) < 0
                    && "定时发布".equals(mapper.getStatus(h.getWid(),h.getCid()))) {
                mapper.statusToRelease(h.getWid(),h.getCid());
            }
        }
    }

    @Override
    @Async
    @Scheduled(cron = "*/2  *  *  *  *  ?")//2秒执行一次
    public void statusToEnd() {
        List<HomeworkInfo> list = mapper.getAll();
        Date nowTime = new Date();
        for (HomeworkInfo h: list){
            if(nowTime.compareTo(h.getEndTime()) >= 0
                    && "已发布".equals(mapper.getStatus(h.getWid(),h.getCid()))) {
                mapper.statusToEnd(h.getWid(),h.getCid());
            }
        }
    }

    @Override
    public String getDeadline(Integer wid, Integer cid) {
        String deadline = mapper.getDeadline(wid,cid);
        if(deadline==null) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");
        }return deadline;
    }

    @Override
    public void addEvaNumber(Integer wid, Integer cid) {
        mapper.addEvaNumber(wid,cid);
    }
}

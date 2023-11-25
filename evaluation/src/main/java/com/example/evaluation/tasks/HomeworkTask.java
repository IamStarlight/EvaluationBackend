package com.example.evaluation.tasks;

import com.example.evaluation.controller.dto.HomeworkInfo;
import com.example.evaluation.entity.Homework;
import com.example.evaluation.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableAsync
@EnableScheduling
public class HomeworkTask {

    @Autowired
    private WorkMapper mapper;

    @Async
    @Scheduled(cron = "*/2  *  *  *  *  ?")//2秒执行一次
    public void statusToRelease() {
        // TODO: 2023-11-24 已截止的修改时间后更改发布状态了吗+evaStatus
        List<Homework> list = mapper.getHomeWorkUsingTimer();
        Date nowTime = new Date();
        for (Homework h: list){
            if(nowTime.compareTo(h.getStartTime()) >= 0
                    && nowTime.compareTo(h.getEndTime()) < 0) {
                mapper.statusToRelease(h.getWid(),h.getCid());
                // TODO: 2023-11-23 发作业发布通知
            }
        }
    }
    // TODO: 2023-11-24 appeal——time设置 

    @Async
    @Scheduled(cron = "*/2  *  *  *  *  ?")//2秒执行一次
    public void statusToEnd() {
        List<HomeworkInfo> list = mapper.getHomeworkHasReleased();
        Date nowTime = new Date();
        for (HomeworkInfo h: list){
            if(nowTime.compareTo(h.getEndTime()) >= 0) {
                mapper.statusToEnd(h.getWid(),h.getCid());
                // TODO: 2023-11-23 发作业截止通知
            }
        }
    }

    @Async
    @Scheduled(cron = "*/2  *  *  *  *  ?")//2秒执行一次
    public void evaStatusToEnd() {
        List<Homework> list = mapper.getHomeworkOpenEva();
        Date nowTime = new Date();
        for (Homework h: list){
            if(nowTime.compareTo(h.getEvaDdl()) >= 0) {
                mapper.evaStatusToEnd(h.getWid(),h.getCid());
                // TODO: 2023-11-23 发互评截止通知
            }
        }
    }

}

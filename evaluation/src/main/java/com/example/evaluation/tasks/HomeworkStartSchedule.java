package com.example.evaluation.tasks;

import com.example.evaluation.exception.ServiceException;
import com.example.evaluation.mapper.CronMapper;
import com.example.evaluation.service.impl.WorkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
public class HomeworkStartSchedule implements SchedulingConfigurer {

    private Integer wid;

    private Integer cid;

    @Autowired
    private CronMapper cronMapper;

    @Autowired
    private WorkServiceImpl workService;

    HomeworkStartSchedule(){

    }

    public HomeworkStartSchedule(Integer wid, Integer cid){
        this.wid = wid;
        this.cid = cid;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
    {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
//                () -> task(),
                this::task,
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronMapper.getStartCron(wid,cid);
                    //2.2 合法性校验.
                    if (cron.isEmpty()) {
                        throw new ServiceException(HttpStatus.FORBIDDEN.value(),"Cron为空");
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

    private void task(){
        //将作业状态改为发布
        workService.statusToRelease(wid,cid);
        // TODO: 2023-11-22 发送作业发布消息
    }
}

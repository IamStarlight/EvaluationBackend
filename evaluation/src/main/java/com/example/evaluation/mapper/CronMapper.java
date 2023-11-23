package com.example.evaluation.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface CronMapper {

    @Select("select cron from cron where function=1")
    public List<String> getStartCron();

    @Select("select cron from cron where function=2")
    public List<String> getEndCron();

    @Insert("insert into cron(wid,cid,function,cron) values(#{wid},#{cid},1,#{cron})")
    public void insertStartCron(Integer wid,Integer cid,String cron);

    @Insert("insert into cron(wid,cid,function,cron) values(#{wid},#{cid},2,#{cron})")
    public void insertEndCron(Integer wid,Integer cid,String cron);

    @Update("update cron set cron=#{cron} where wid=#{wid} and cid=#{cid} and function=1")
    public void updateStartCron(Integer wid,Integer cid,Integer function,String cron);

    @Update("update cron set cron=#{cron} where wid=#{wid} and cid=#{cid} and function=2")
    public void updateEndCron(Integer wid,Integer cid,Integer function,String cron);
}

package com.lantu.domain.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.User;
import com.lantu.domain.mapper.UserMapper;
import com.lantu.domain.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
//import com.fasterxml.jackson.datatype:jackson-datatype-jsr310;
import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gxccc
 * @since 2023-05-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Map<String,Object>login(User user){

        //根据用户名和密码查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        wrapper.eq(User::getPassword,user.getPassword());
        User user1 = this.baseMapper.selectOne(wrapper);
        //结果不为空，则生成token , 并将用户的信息存入redis
        if(user1 != null){
            //生成token,我们这里暂时随意生产,暂时使用uuid 什么事jwt
            String key = "user:" + UUID.randomUUID();
            //存入redis
            user1.setPassword(null);
//            user1.setRegTime(null);
            redisTemplate.opsForValue().set(key,user1,30, TimeUnit.MINUTES);
            //返回数据
            Map<String,Object> data = new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }

    @Override
    public User getUserInfo(String token) {
        System.out.println(token);
        //根据token来获取信息
        Object object = redisTemplate.opsForValue().get(token);

        if(object != null){

            User user1 = JSON.parseObject(JSON.toJSONString(object),User.class);
            User user = this.baseMapper.selectById(user1);
            System.out.println(user);
            return user;

        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    @Override
    public boolean addUser(User user) {
        int a = this.baseMapper.insert(user);
        if(a==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(User user,String token) {
        Object object = redisTemplate.opsForValue().get(token);
        if(object != null){
            User user1 = JSON.parseObject(JSON.toJSONString(object),User.class);
            user1.setPassword(user.getPassword());
            this.baseMapper.updateById(user1);
            return true;
        }
        return false;
    }

    public boolean changeEmail(User user,String token) {
        Object object = redisTemplate.opsForValue().get(token);
        if(object != null){
            User user1 = JSON.parseObject(JSON.toJSONString(object),User.class);
            user1.setEmail(user.getEmail());
            this.baseMapper.updateById(user1);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeUser(User user) {
        this.baseMapper.updateById(user);
        return true;
    }

    @Override
    public boolean changeUserface(User user, String token) {
        Object object = redisTemplate.opsForValue().get(token);
        if(object != null){
            User user1 = JSON.parseObject(JSON.toJSONString(object),User.class);
//            user1.setUserface(user.getUserface());
            this.baseMapper.updateById(user1);
            return true;
        }
        return false;
    }

    @Override
    public String returnUploadToken() {
        String ak = "RvSSLZzyvWnQoHd6qWRNJpV4E3ti2Ifemsxj6Xvc";
        String sk = "GH2eDLZEJAItCNQqORoqjcfH3jzFqZo8Z_TqPKQm";
        String bucketName= "jsdn2023";
        Auth auth = Auth.create(ak, sk);
        String uploadToken = auth.uploadToken(bucketName);
        return uploadToken;
    }

    @Override
    public boolean alterUser(User user) {
        this.baseMapper.updateById(user);
        return true;
    }

    @Override
    public  List<User> getSpecUserInfo(int id) {
        Map<String,Object> re = new HashMap<>();
        re.put("id",id);
        List<User> users = this.baseMapper.selectByMap(re);
        return users;
    }

    @Override
    public boolean removeUser(User user) {
        Map<String,Object>  de= new HashMap<>();
        de.put("username",user.getUsername());
        if(this.baseMapper.deleteByMap(de)!=0){
            return true;
        }
        return false;
    }


}



package com.lantu.domain.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.User;
import com.lantu.domain.mapper.ArticleMapper;
import com.lantu.domain.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.bytebuddy.implementation.InvokeDynamic;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.ReactiveTransaction;
import org.yaml.snakeyaml.events.Event;

import java.awt.print.Book;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Override
    public Map<String, Object> search(User user){
        return null;
    }
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<Article> getMyArticle(String token){
        Object object = redisTemplate.opsForValue().get(token);
        if(object != null){
            User user1 = JSON.parseObject(JSON.toJSONString(object),User.class);
//            String name = user1.getUsername();
//            List<Integer> uid = this.baseMapper.getUidByUserName(name);
            Map<String, Object> params = new HashMap<>();
            params.put("user_name", user1.getUsername());
            List<Article> articles = this.baseMapper.selectByMap(params);
            if(articles != null){
                return articles;
            }

        }

        return  null;
    }

    @Override
    public List<Article> searchArticle(Article article) {
        List<Article> articles  = this.baseMapper.fuzzyQueryBytitle("%"+article.getTitle()+"%");
        return articles;
    }

    @Override
    public boolean addArticle(Article article,String token) {
        Object object = redisTemplate.opsForValue().get(token);
        if(object != null) {
            User user1 = JSON.parseObject(JSON.toJSONString(object), User.class);
            article.setUserName(user1.getUsername());
            article.setXihuan(0);
            article.setCollect(0);
        }
//        article.setCid(this.baseMapper.getcid(article.getC));

        int a = this.baseMapper.insert(article);
        if(a==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean removeArticle(Article article) {
        Map<String,Object>  de= new HashMap<>();
        de.put("id",article.getId());
        if(this.baseMapper.deleteByMap(de)!=0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addCollect(Article article) {
        Map<String,Object> ad = new HashMap<>();
        ad.put("id",article.getId());
        List<Article> articles = this.baseMapper.selectByMap(ad);
        for (Article a:articles){
            a.setCollect(a.getCollect()+1);
            this.baseMapper.updateById(a);
            return true;
        }
        return false;
    }

    @Override
    public boolean addLike(Article article) {
        Map<String,Object> ad = new HashMap<>();
        ad.put("id",article.getId());
        List<Article> articles = this.baseMapper.selectByMap(ad);
        for (Article a:articles){
            System.out.print(a.getXihuan());
            a.setXihuan(a.getXihuan()+1);
            System.out.print(a.getXihuan());
            this.baseMapper.updateById(a);
            return  true;
        }
        return false;
    }

    @Override
    public List<Article> showPage(int id) {
        Map<String,Object> ad = new HashMap<>();
        ad.put("id",id);
        List<Article> articles = this.baseMapper.selectByMap(ad);
        if(articles!= null){
            return articles;
        }
        return null;
    }

    @Override
    public Article searchArticlebyid(int id) {
        Article a = new Article();
        a.setId(id);
        Article articles  = this.baseMapper.selectById(a);
        if(articles != null){
            return articles;
        }
        return null;
    }

    @Override
    public boolean removeCollect(Article article) {
        Map<String,Object> ad = new HashMap<>();
        ad.put("id",article.getId());
        List<Article> articles = this.baseMapper.selectByMap(ad);
        for (Article a:articles){
            if(a.getCollect() == 0){
                return false;
            }
            a.setCollect(a.getCollect()-1);
            this.baseMapper.updateById(a);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeLike(Article article) {
        Map<String,Object> ad = new HashMap<>();
        ad.put("id",article.getId());
        List<Article> articles = this.baseMapper.selectByMap(ad);
        for (Article a:articles){
            if(a.getXihuan() == 0){
                return false;
            }
            a.setXihuan(a.getXihuan()-1);
            this.baseMapper.updateById(a);
            return true;
        }
        return false;
    }

    @Override
    public boolean addView(Article article) {
        Map<String,Object> ad = new HashMap<>();
        ad.put("id",article.getId());
        List<Article> articles = this.baseMapper.selectByMap(ad);
        for (Article a:articles){
            System.out.print(a.getView());
            a.setView(a.getView()+1);
            System.out.print(a.getView());
            this.baseMapper.updateById(a);
            return  true;
        }
        return false;
    }

    @Override
    public boolean alterArticle(Article article) {
        this.baseMapper.updateById(article);
        return true;
    }
}


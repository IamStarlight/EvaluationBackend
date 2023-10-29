package com.lantu.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.CollectArt;
import com.lantu.domain.mapper.CollectArtMapper;
import com.lantu.domain.service.ICollectArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gxccc
 * @since 2023-06-16
 */
@Service
public class CollectArtServiceImpl extends ServiceImpl<CollectArtMapper, CollectArt> implements ICollectArtService {

    @Autowired
    private  ArticleServiceImpl articleService;
    @Override
    public List<Article> getUserCollect(int uid) {
        Map<String,Object> re = new HashMap<>();
        List<CollectArt> collectArts = this.baseMapper.selectByMap(re);
        List<Article> ans= new ArrayList<>();
        for(CollectArt a:collectArts){
            Article article = articleService.searchArticlebyid(a.getAid());
            ans.add(article);
        }
        return ans;
    }



    @Override
    public boolean removeCol(Article article) {
        Map<String,Object> re = new HashMap<>();
        re.put("aid",article.getId());
        articleService.removeArticle(article);
        int i = this.baseMapper.deleteByMap(re);
        if(i != 0){
            return true;
        }
        return false;
    }
}

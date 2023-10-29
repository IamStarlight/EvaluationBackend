package com.lantu.domain.service;

import com.lantu.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lantu.domain.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gxccc
 * @since 2023-05-29
 */
public interface IArticleService extends IService<Article> {

    Map<String, Object> search(User user);
    List<Article> getMyArticle(String token);

    List<Article> searchArticle(Article article);

    boolean addArticle(Article article,String token);

    boolean removeArticle(Article article);

    boolean addCollect(Article article);

    boolean addLike(Article article);

    List<Article> showPage(int id);

    Article searchArticlebyid(int id);

    boolean removeCollect(Article article);

    boolean removeLike(Article article);

    boolean addView(Article article);

    boolean alterArticle(Article article);
}

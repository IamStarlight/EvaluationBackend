package com.lantu;

import com.lantu.domain.controller.ArticleController;
import com.lantu.domain.controller.CommentsController;
import com.lantu.domain.controller.UserController;
import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.Comments;
import com.lantu.domain.entity.Tags;
import com.lantu.domain.entity.User;
import com.lantu.domain.mapper.ArticleMapper;
import com.lantu.domain.mapper.TagsMapper;
import com.lantu.domain.mapper.UserMapper;
import com.lantu.domain.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class JiaoSdnApplicationTests {

    @Resource
    private TagsMapper test;
    @Resource
    private ArticleServiceImpl articleService;
    @Resource
    private CommentsController cc;
    @Resource
    private UserController uc;
    @Resource
    private  ArticleController ac;

//    @Test
//    public void contextLoads() {
//        List<Tags> s = test.selectList(null);
//        s.forEach(System.out::println);
//    }

    @Test
    public void articleTest(){
//        User user = new User();
//        user.setUsername("linghu");
//        Article article = new Article();
//        article.setId(108);
//        article.setTitle("linux");
////        List<Article>list = articleService.getMyArticle(user);
//        List<Article> list = articleService.searchArticle(article);
//
//
//        ac.getAllArticile();
//            ac.getMyArticle("user:6ee07aa8-7cb5-4152-b666-5fac96e58ab5");
//        ac.addLike(article);
//        ac.specComments(108);
//        ac.
        Comments comments  = new Comments();
        comments.setContent("YYYYY");
        cc.addComments(comments);


    }

}

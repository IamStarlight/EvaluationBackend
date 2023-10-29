package com.lantu.domain.controller;

import com.lantu.common.vo.Result;
import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.Comments;
import com.lantu.domain.service.IArticleService;
import com.lantu.domain.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gxccc
 * @since 2023-05-29
 */
@Controller
@RestController
@CrossOrigin
@RequestMapping("/domain/articleTags")
public class ArticleTagsController {
    @Autowired
    private IArticleService articleService;
    @GetMapping("/all")
    public Result<List<Article>> getAllArticile(){
        List<Article>list = articleService.list();
        return Result.success(list);
    }
}

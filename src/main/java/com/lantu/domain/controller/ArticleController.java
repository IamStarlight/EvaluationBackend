package com.lantu.domain.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantu.common.vo.Result;
import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.Comments;
import com.lantu.domain.entity.User;
import com.lantu.domain.service.IArticleService;
import com.lantu.domain.service.ICommentsService;
import com.lantu.domain.service.IUserService;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gxccc
 * @since 2023-05-29
 */
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/domain/article")
public class ArticleController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IArticleService articleService;
    @GetMapping("/all")
    public Result<List<Article>> getAllArticile(){
        List<Article>list = articleService.list();
        return Result.success(list);
    }
    @GetMapping("/search")
    public Result<List<Article>> searchArticile(@RequestParam("title") String title){
        Article article = new Article();
        article.setTitle(title);
        List<Article> data =  articleService.searchArticle(article);
        if(data != null){
            return Result.success(data);
        }
        return Result.error(20001,"搜索失败");
    }

    @GetMapping("/getOneArticle")
    public Result<Article> searchArticile(@RequestParam("id") int id){
        Article data =  articleService.searchArticlebyid(id);
        if(data != null){
            return Result.success(data);
        }
        return Result.error(20001,"搜索失败");
    }
    @GetMapping("/myself")
    public Result<List<Article>> getMyArticle(@RequestHeader ("token") String token){
        List<Article> data =  articleService.getMyArticle(token);
        if(data != null){
            return Result.success(data);
        }
        return Result.error(20001,"用户名或密码错误");
    }

    @PostMapping ("/add")
    public  Result<Object> addArticle(@RequestBody Article article,@RequestHeader ("token") String token){
        boolean a = articleService.addArticle(article,token);
        if(a){
            return Result.success();
        }
        return Result.error(20004,"插入失败");
    }

    @PostMapping ("/remove")
    public  Result<Object> removeArticle(@RequestBody Article article){
        boolean a = articleService.removeArticle(article);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"删除失败");
    }

    @PostMapping ("/alterArticle")
    public  Result<Object> alterArticle(@RequestBody Article article){
        boolean a = articleService.alterArticle(article);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"删除失败");
    }


    @PostMapping ("/addCollect")
    public  Result<Object> addCollect(@RequestBody Article article){
        boolean a = articleService.addCollect(article);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"增加收藏失败");
    }

    @PostMapping ("/removeCollect")
    public  Result<Object> removeCollect(@RequestBody Article article){
        boolean a = articleService.removeCollect(article);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"增加收藏失败");
    }

    @GetMapping ("/showPage")
    public  Result<Object> showPage(@RequestHeader ("id") int id ){
        List<Article> data =  articleService.showPage(id);
        if(data != null){
            return Result.success(data);
        }
        return Result.error(20001,"选择文章错误");
    }

    @PostMapping ("/addXihuan")
    public  Result<Object> addLike(@RequestBody Article article){
        boolean a = articleService.addLike(article);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"增加喜欢失败");
    }

    @PostMapping ("/addView")
    public  Result<Object> addView(@RequestBody Article article){
        boolean a = articleService.addView(article);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"增加喜欢失败");
    }

    @PostMapping ("/removeXihuan")
    public  Result<Object> removeLike(@RequestBody Article article){
        boolean a = articleService.removeLike(article);

        if(a){
            return Result.success();
        }
        return Result.error(20005,"取消喜欢失败");
    }




}

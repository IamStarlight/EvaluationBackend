package com.lantu.domain.controller;

import com.lantu.common.vo.Result;
import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.Comments;
import com.lantu.domain.service.IArticleService;
import com.lantu.domain.service.ICollectArtService;
import com.lantu.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gxccc
 * @since 2023-06-16
 */
@Controller
@RequestMapping("/domain/collectArt")
@CrossOrigin
@ResponseBody
public class CollectArtController {

    @Autowired
    private ICollectArtService iCollectArtService;

    @GetMapping("/getUserCollect")
    public Result<List<Article>> getUserArticle(@RequestParam("uid") int uid){
        List<Article> data = iCollectArtService.getUserCollect(uid);
        if(data != null){
            return Result.success(data);
        }
        return Result.error(20001,"搜索失败");
    }

    @PostMapping("/removeCollect")
    public Result<Object> removeOneCollect(@RequestBody Article article){
        boolean a = iCollectArtService.removeCol(article);
        if(a){
            return Result.success();
        }
        return Result.error("失败了");
    }


}

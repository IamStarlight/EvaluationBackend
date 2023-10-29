package com.lantu.domain.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantu.common.vo.Result;
import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.Comments;
import com.lantu.domain.entity.User;
import com.lantu.domain.service.ICommentsService;
import com.lantu.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@ResponseBody
@CrossOrigin
@RequestMapping("/domain/comments")
public class CommentsController {
    @Autowired
    private ICommentsService commentsService ;

    @GetMapping("/getAllComments")
    public Result<List<Comments>> allComments(){
        List<Comments> list = commentsService.list();
        return Result.success(list);
    }
    @GetMapping("/getSpecComments")
    public Result<List<Comments>> specComments(@RequestParam("aid") int aid){
        List<Comments> data = commentsService.searchCommentsbyaid(aid);
        if(data != null){
            return Result.success(data);
        }
        return Result.error(20001,"搜索失败");
    }



    @PostMapping("/add")
    public  Result<Object> addComments(@RequestBody Comments comments){
        comments.setParentId(null);
        boolean a = commentsService.addComments(comments);
        return Result.success("插入成功");
    }

    @PostMapping("/remove")
    public  Result<Object> removeComments(@RequestBody Comments comments){
        boolean a = commentsService.removeComments(comments);
        if(a){
            return Result.success();
        }
        return Result.error(20005,"删除失败");
    }

}

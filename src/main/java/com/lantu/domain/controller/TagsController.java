package com.lantu.domain.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantu.common.vo.Result;
import com.lantu.domain.entity.Tags;
import com.lantu.domain.entity.User;
import com.lantu.domain.service.ITagsService;
import com.lantu.domain.service.IUserService;
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
@CrossOrigin
@RestController
@RequestMapping("/domain/tags")
public class TagsController {

    @Autowired
    private ITagsService tagsService;
    @GetMapping("/all")
    public Result<List<Tags>> getAllTag(){
        List<Tags>list = tagsService.list();
        return Result.success(list);
    }

}

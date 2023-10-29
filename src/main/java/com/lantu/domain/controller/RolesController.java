package com.lantu.domain.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantu.common.vo.Result;
import com.lantu.domain.entity.Roles;
import com.lantu.domain.entity.Tags;
import com.lantu.domain.service.IRolesService;
import com.lantu.domain.service.ITagsService;
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
@RequestMapping("/domain/roles")
public class RolesController {

    @Autowired
    private IRolesService rolesService;
    @GetMapping("/all")
    public Result<List<Roles>> getAllroles(){
        List<Roles>list = rolesService.list();
        return Result.success(list);
    }

}

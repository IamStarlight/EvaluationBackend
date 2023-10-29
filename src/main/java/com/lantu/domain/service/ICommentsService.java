package com.lantu.domain.service;

import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.Comments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gxccc
 * @since 2023-05-29
 */
public interface ICommentsService extends IService<Comments> {


    boolean removeComments(Comments comments);

    boolean addComments(Comments comments);

    List<Comments> searchCommentsbyaid(int id);
}

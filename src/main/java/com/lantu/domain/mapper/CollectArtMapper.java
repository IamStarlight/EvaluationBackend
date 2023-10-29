package com.lantu.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lantu.domain.entity.Article;
import com.lantu.domain.entity.CollectArt;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxccc
 * @since 2023-06-16
 */
public interface CollectArtMapper extends BaseMapper<CollectArt> {
    public List<Article> getcollect(int uid);
}

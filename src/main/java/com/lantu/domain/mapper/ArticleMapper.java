package com.lantu.domain.mapper;

import com.lantu.domain.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxccc
 * @since 2023-05-29
 */
public interface ArticleMapper extends BaseMapper<Article> {
    public List<Integer> getUidByUserName(String userName);

    public List<Article> fuzzyQueryBytitle(String title);
    public List<Integer> getcid(String cate_name);
}

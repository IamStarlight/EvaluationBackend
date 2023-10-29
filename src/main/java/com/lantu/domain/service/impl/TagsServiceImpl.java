package com.lantu.domain.service.impl;

import com.lantu.domain.entity.Tags;
import com.lantu.domain.mapper.TagsMapper;
import com.lantu.domain.service.ITagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gxccc
 * @since 2023-05-29
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {

}

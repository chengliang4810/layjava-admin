package com.layjava.system.service.impl;

import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.bo.SysPostBo;
import com.layjava.system.domain.vo.SysPostVo;
import com.layjava.system.service.ISysPostService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.List;

@Slf4j
@Component
public class SysPostServiceImpl implements ISysPostService {

    @Override
    public PageResult<SysPostVo> selectPagePostList(SysPostBo post, PageQuery pageQuery) {
        return null;
    }

    @Override
    public List<SysPostVo> selectPostList(SysPostBo post) {
        return List.of();
    }

    @Override
    public List<SysPostVo> selectPostAll() {
        return List.of();
    }

    @Override
    public SysPostVo selectPostById(Long postId) {
        return null;
    }

    @Override
    public List<Long> selectPostListByUserId(Long userId) {
        return List.of();
    }

    @Override
    public boolean checkPostNameUnique(SysPostBo post) {
        return false;
    }

    @Override
    public boolean checkPostCodeUnique(SysPostBo post) {
        return false;
    }

    @Override
    public long countUserPostById(Long postId) {
        return 0;
    }

    @Override
    public int deletePostById(Long postId) {
        return 0;
    }

    @Override
    public int deletePostByIds(Long[] postIds) {
        return 0;
    }

    @Override
    public int insertPost(SysPostBo bo) {
        return 0;
    }

    @Override
    public int updatePost(SysPostBo bo) {
        return 0;
    }
}

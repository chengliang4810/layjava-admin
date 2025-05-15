package com.jimuqu.system.service.impl;

import com.jimuqu.common.mybatis.core.Page;
import com.jimuqu.common.mybatis.core.page.PageQuery;
import com.jimuqu.system.domain.bo.SysPostBo;
import com.jimuqu.system.domain.vo.SysPostVo;
import com.jimuqu.system.service.ISysPostService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;

import java.util.List;

@Slf4j
@Component
public class SysPostServiceImpl implements ISysPostService {

    @Override
    public Page<SysPostVo> selectPagePostList(SysPostBo post, PageQuery pageQuery) {
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

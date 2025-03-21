package com.layjava.system.service.impl;

import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.system.domain.SysSocial;
import com.layjava.system.domain.bo.SysSocialBo;
import com.layjava.system.domain.vo.SysSocialVo;
import com.layjava.system.mapper.SysSocialMapper;
import com.layjava.system.service.ISysSocialService;
import com.mybatisflex.core.query.QueryWrapper;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

import static com.layjava.system.domain.table.SysSocialTableDef.SYS_SOCIAL;

/**
 * 社会化关系Service业务层处理
 *
 * @author thiszhc
 * @date 2023-06-12
 */

@Component
public class SysSocialServiceImpl implements ISysSocialService {

    @Inject
    private SysSocialMapper baseMapper;


    /**
     * 查询社会化关系
     */
    @Override
    public SysSocialVo queryById(String id) {
        return baseMapper.selectOneWithRelationsByIdAs(id, SysSocialVo.class);
    }

    /**
     * 授权列表
     */
    @Override
    public List<SysSocialVo> queryList() {
        return baseMapper.selectListByQueryAs(QueryWrapper.create().from(SYS_SOCIAL), SysSocialVo.class);
    }

    @Override
    public List<SysSocialVo> queryListByUserId(Long userId) {
        return baseMapper.selectListByQueryAs(QueryWrapper.create().from(SYS_SOCIAL).where(SYS_SOCIAL.USER_ID.eq(userId)), SysSocialVo.class);
    }


    /**
     * 新增社会化关系
     */
    @Override
    public Boolean insertByBo(SysSocialBo bo) {
        SysSocial add = MapstructUtil.convert(bo, SysSocial.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add, true) > 0;
        if (flag) {
            if (add != null) {
                bo.setId(add.getId());
            } else {
                return false;
            }
        }
        return flag;
    }

    /**
     * 更新社会化关系
     */
    @Override
    public Boolean updateByBo(SysSocialBo bo) {
        SysSocial update = MapstructUtil.convert(bo, SysSocial.class);
        validEntityBeforeSave(update);
        return baseMapper.update(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysSocial entity) {
        //TODO 做一些数据校验,如唯一约束
    }


    /**
     * 删除社会化关系
     */
    @Override
    public Boolean deleteWithValidById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    /**
     * 根据 authId 查询用户信息
     *
     * @param authId 认证id
     * @return 授权信息
     */
    @Override
    public List<SysSocialVo> selectByAuthId(String authId) {
        return baseMapper.selectListByQueryAs(QueryWrapper.create().from(SYS_SOCIAL).where(SYS_SOCIAL.AUTH_ID.eq(authId)), SysSocialVo.class);
    }

}

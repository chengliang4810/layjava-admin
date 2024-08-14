package com.layjava.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.utils.MapstructUtils;
import com.layjava.common.core.utils.StringUtils;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysOssConfig;
import com.layjava.system.domain.bo.SysOssConfigBo;
import com.layjava.system.domain.vo.SysOssConfigVo;
import com.layjava.system.mapper.SysOssConfigMapper;
import com.layjava.system.service.ISysOssConfigService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;

import java.util.Collection;
import java.util.List;

import static com.layjava.system.domain.table.SysOssConfigTableDef.SYS_OSS_CONFIG;

/**
 * 对象存储配置Service业务层处理
 *
 * @author Lion Li
 * @author 孤舟烟雨
 * @date 2021-08-13
 */
@Slf4j
@Component
public class SysOssConfigServiceImpl implements ISysOssConfigService {

    @Db
    private SysOssConfigMapper baseMapper;

    /**
     * 项目启动时，初始化参数到缓存，加载配置类
     */
    @Override
    public void init() {
        List<SysOssConfig> list = baseMapper.selectAll();
        // 加载OSS初始化配置
        for (SysOssConfig config : list) {
            String configKey = config.getConfigKey();
            if ("0".equals(config.getStatus())) {
                // RedisUtils.setCacheObject(OssConstant.DEFAULT_CONFIG_KEY, configKey);
            }
            // CacheUtils.put(CacheNames.SYS_OSS_CONFIG, config.getConfigKey(), JsonUtils.toJsonString(config));
        }

    }

    @Override
    public SysOssConfigVo queryById(Long ossConfigId) {
        return baseMapper.selectOneWithRelationsByIdAs(ossConfigId, SysOssConfigVo.class);
    }

    @Override
    public PageResult<SysOssConfigVo> queryPageList(SysOssConfigBo bo, PageQuery pageQuery) {
        QueryWrapper lqw = buildQueryWrapper(bo);
        Page<SysOssConfigVo> result = baseMapper.paginateAs(pageQuery, lqw, SysOssConfigVo.class);
        return PageResult.build(result);
    }


    private QueryWrapper buildQueryWrapper(SysOssConfigBo bo) {
        return QueryWrapper.create().from(SYS_OSS_CONFIG)
                .where(SYS_OSS_CONFIG.CONFIG_KEY.eq(bo.getConfigKey()))
                .and(SYS_OSS_CONFIG.BUCKET_NAME.like(bo.getBucketName()))
                .and(SYS_OSS_CONFIG.STATUS.eq(bo.getStatus()))
                .orderBy(SYS_OSS_CONFIG.OSS_CONFIG_ID, true);
    }

    @Override
    public Boolean insertByBo(SysOssConfigBo bo) {
        SysOssConfig config = MapstructUtils.convert(bo, SysOssConfig.class);
        validEntityBeforeSave(config);
        boolean flag = baseMapper.insert(config, true) > 0;
        if (flag) {
            // 从数据库查询完整的数据做缓存
            config = baseMapper.selectOneById(config.getOssConfigId());
            // CacheUtils.put(CacheNames.SYS_OSS_CONFIG, config.getConfigKey(), JsonUtils.toJsonString(config));
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(SysOssConfigBo bo) {
        SysOssConfig config = MapstructUtils.convert(bo, SysOssConfig.class);
        validEntityBeforeSave(config);
        boolean update = baseMapper.update(config, false) != 0;

        if (update) {
            // 从数据库查询完整的数据做缓存
            config = baseMapper.selectOneById(config.getOssConfigId());
            // CacheUtils.put(CacheNames.SYS_OSS_CONFIG, config.getConfigKey(), JsonUtils.toJsonString(config));
        }
        return update;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysOssConfig entity) {
        if (StringUtils.isNotEmpty(entity.getConfigKey())
                && !checkConfigKeyUnique(entity)) {
            throw new ServiceException("操作配置'" + entity.getConfigKey() + "'失败, 配置key已存在!");
        }
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
//            if (CollUtil.containsAny(ids, OssConstant.SYSTEM_DATA_IDS)) {
//                throw new ServiceException("系统内置, 不可删除!");
//            }
        }
        List<SysOssConfig> list = CollUtil.newArrayList();
        for (Long configId : ids) {
            SysOssConfig config = baseMapper.selectOneById(configId);
            list.add(config);
        }
        boolean flag = baseMapper.deleteBatchByIds(ids) > 0;
        if (flag) {
//            list.forEach(sysOssConfig ->
//                    CacheUtils.evict(CacheNames.SYS_OSS_CONFIG, sysOssConfig.getConfigKey())
//            );
        }
        return flag;
    }

    /**
     * 判断configKey是否唯一
     */
    private boolean checkConfigKeyUnique(SysOssConfig sysOssConfig) {
        long ossConfigId = ObjectUtil.isNull(sysOssConfig.getOssConfigId()) ? -1L : sysOssConfig.getOssConfigId();
        SysOssConfig info = baseMapper.selectOneByQuery(
                QueryWrapper.create().select(SYS_OSS_CONFIG.OSS_CONFIG_ID, SYS_OSS_CONFIG.CONFIG_KEY).from(SYS_OSS_CONFIG)
                        .where(SYS_OSS_CONFIG.CONFIG_KEY.eq(sysOssConfig.getConfigKey())));
        if (ObjectUtil.isNotNull(info) && info.getOssConfigId() != ossConfigId) {
            return false;
        }
        return true;
    }

    /**
     * 启用禁用状态
     */
    @Override
    //@Transactional(rollbackFor = Exception.class)
    public int updateOssConfigStatus(SysOssConfigBo bo) {
        SysOssConfig sysOssConfig = MapstructUtils.convert(bo, SysOssConfig.class);
        boolean updateOldStatus = UpdateChain.of(SysOssConfig.class).set(SysOssConfig::getStatus, "1")
                .where(SysOssConfig::getStatus).eq("0")
                .update();
        int row = baseMapper.update(sysOssConfig);
        if (updateOldStatus || row > 0) {
            // RedisUtils.setCacheObject(OssConstant.DEFAULT_CONFIG_KEY, sysOssConfig.getConfigKey());
        }
        return row;
    }

}

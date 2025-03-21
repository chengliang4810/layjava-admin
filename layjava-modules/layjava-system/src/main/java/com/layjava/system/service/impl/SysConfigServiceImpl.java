package com.layjava.system.service.impl;

import com.layjava.common.core.constant.UserConstants;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.service.ConfigService;
import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysConfig;
import com.layjava.system.domain.bo.SysConfigBo;
import com.layjava.system.domain.vo.SysConfigVo;
import com.layjava.system.mapper.SysConfigMapper;
import com.layjava.system.service.ISysConfigService;
import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.dromara.hutool.core.convert.ConvertUtil;
import org.dromara.hutool.core.util.ObjUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.layjava.system.domain.table.SysConfigTableDef.SYS_CONFIG;

/**
 * 参数配置 服务层实现
 *
 * @author Lion Li
 */

@Component
public class SysConfigServiceImpl implements ISysConfigService, ConfigService {

    @Inject
    private SysConfigMapper baseMapper;

    @Override
    public PageResult<SysConfigVo> selectPageConfigList(SysConfigBo config, PageQuery pageQuery) {
        QueryWrapper lqw = buildQueryWrapper(config);
        Page<SysConfigVo> page = baseMapper.paginateAs(pageQuery, lqw, SysConfigVo.class);
        return PageResult.build(page);
    }

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    @UseDataSource("master" )
    public SysConfigVo selectConfigById(Long configId) {
        return baseMapper.selectOneWithRelationsByIdAs(configId, SysConfigVo.class);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    // @Cacheable(cacheNames = CacheNames.SYS_CONFIG, key = "#configKey")
    @Override
    public String selectConfigByKey(String configKey) {
        SysConfig retConfig = baseMapper.selectOneByQuery(QueryWrapper.create().from(SYS_CONFIG)
                .where(SYS_CONFIG.CONFIG_KEY.eq(configKey)));
        if (ObjUtil.isNotNull(retConfig)) {
            return retConfig.getConfigValue();
        }
        return StringUtil.EMPTY;
    }

    /**
     * 获取注册开关
     *
     * @return true开启，false关闭
     */
    @Override
    public boolean selectRegisterEnabled() {
        SysConfig retConfig = baseMapper.selectOneByQuery(QueryWrapper.create().from(SYS_CONFIG)
                .where(SYS_CONFIG.CONFIG_KEY.eq("sys.account.registerUser" )));
        if (ObjUtil.isNull(retConfig)) {
            return false;
        }
        return ConvertUtil.toBoolean(retConfig.getConfigValue());
    }

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfigVo> selectConfigList(SysConfigBo config) {
        QueryWrapper lqw = buildQueryWrapper(config);
        return baseMapper.selectListByQueryAs(lqw, SysConfigVo.class);
    }

    private QueryWrapper buildQueryWrapper(SysConfigBo bo) {
        Map<String, Object> params = bo.getParams();
        return QueryWrapper.create().from(SYS_CONFIG)
                .where(SYS_CONFIG.CONFIG_NAME.like(bo.getConfigName()))
                .and(SYS_CONFIG.CONFIG_TYPE.eq(bo.getConfigType()))
                .where(SYS_CONFIG.CONFIG_KEY.like(bo.getConfigKey()))
                .and(SYS_CONFIG.CREATE_TIME.between(params.get("beginTime" ), params.get("endTime" ), params.get("beginTime" ) != null && params.get("endTime" ) != null))
                .orderBy(SYS_CONFIG.CONFIG_ID, true);
    }

    /**
     * 新增参数配置
     *
     * @param bo 参数配置信息
     * @return 结果
     */
    // @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#bo.configKey")
    @Override
    public String insertConfig(SysConfigBo bo) {
        SysConfig config = MapstructUtil.convert(bo, SysConfig.class);
        int row = baseMapper.insert(config, true);
        if (row > 0) {
            return config.getConfigValue();
        }
        throw new ServiceException("操作失败" );
    }

    /**
     * 修改参数配置
     *
     * @param bo 参数配置信息
     * @return 结果
     */
    // @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#bo.configKey")
    @Override
    public String updateConfig(SysConfigBo bo) {
        int row = 0;
        SysConfig config = MapstructUtil.convert(bo, SysConfig.class);
        if (config.getConfigId() != null) {
            SysConfig temp = baseMapper.selectOneById(config.getConfigId());
            if (!StringUtil.equals(temp.getConfigKey(), config.getConfigKey())) {
                // CacheUtils.evict(CacheNames.SYS_CONFIG, temp.getConfigKey());
            }
            row = baseMapper.update(config);
        } else {
            row = baseMapper.updateByQuery(config, QueryWrapper.create().from(SYS_CONFIG).where(SYS_CONFIG.CONFIG_KEY.eq(config.getConfigKey())));
        }
        if (row > 0) {
            return config.getConfigValue();
        }
        throw new ServiceException("操作失败" );
    }

    /**
     * 批量删除参数信息
     *
     * @param configIds 需要删除的参数ID
     */
    @Override
    public void deleteConfigByIds(Long[] configIds) {
        for (Long configId : configIds) {
            SysConfig config = baseMapper.selectOneById(configId);
            if (StringUtil.equals(UserConstants.YES, config.getConfigType())) {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            // CacheUtils.evict(CacheNames.SYS_CONFIG, config.getConfigKey());
        }
        baseMapper.deleteBatchByIds(Arrays.asList(configIds));
    }

    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache() {
        // CacheUtils.clear(CacheNames.SYS_CONFIG);
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public boolean checkConfigKeyUnique(SysConfigBo config) {
        long configId = ObjUtil.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = baseMapper.selectOneByQuery(QueryWrapper.create().from(SYS_CONFIG).where(SYS_CONFIG.CONFIG_KEY.eq(config.getConfigKey())));
        return !ObjUtil.isNotNull(info) || info.getConfigId() == configId;
    }

    /**
     * 根据参数 key 获取参数值
     *
     * @param configKey 参数 key
     * @return 参数值
     */
    @Override
    public String getConfigValue(String configKey) {
        return this.selectConfigByKey(configKey);
    }

}

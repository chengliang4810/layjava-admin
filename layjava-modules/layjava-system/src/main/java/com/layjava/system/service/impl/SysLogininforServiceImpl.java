package com.layjava.system.service.impl;

import com.layjava.common.core.constant.Constants;
import com.layjava.common.core.utils.MapstructUtil;
import com.layjava.common.core.utils.StringUtil;
import com.layjava.common.core.utils.ip.AddressUtil;
import com.layjava.common.log.event.LogininforEvent;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;
import com.layjava.system.domain.SysClient;
import com.layjava.system.domain.SysLogininfor;
import com.layjava.system.domain.bo.SysLogininforBo;
import com.layjava.system.domain.vo.SysLogininforVo;
import com.layjava.system.mapper.SysLogininforMapper;
import com.layjava.system.service.ISysClientService;
import com.layjava.system.service.ISysLogininforService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.util.ObjUtil;
import org.dromara.hutool.http.useragent.UserAgent;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.layjava.system.domain.table.SysLogininforTableDef.SYS_LOGININFOR;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author Lion Li
 */

@Slf4j
@Component
public class SysLogininforServiceImpl implements ISysLogininforService {

    @Inject
    private SysLogininforMapper baseMapper;
    @Inject
    private ISysClientService clientService;

    /**
     * 记录登录信息
     *
     * @param logininforEvent 登录事件
     */
    // @Async
    // @EventListener
    public void recordLogininfor(LogininforEvent logininforEvent) {
        // HttpServletRequest request = logininforEvent.getRequest();
        final UserAgent userAgent = null; // UserAgentUtil.parse(.getHeader("User-Agent"));
        final String ip = null;
        // 客户端信息
        String clientid = null;
        SysClient client = null;
        if (StringUtil.isNotBlank(clientid)) {
            client = clientService.queryByClientId(clientid);
        }

        String address = AddressUtil.getRealAddressByIP(ip);
        StringBuilder s = new StringBuilder();
        s.append(getBlock(ip));
        s.append(address);
        s.append(getBlock(logininforEvent.getUsername()));
        s.append(getBlock(logininforEvent.getStatus()));
        s.append(getBlock(logininforEvent.getMessage()));
        // 打印信息到日志
        log.info(s.toString(), logininforEvent.getArgs());
        // 获取客户端操作系统
        String os = userAgent.getOs().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        // 封装对象
        SysLogininforBo logininfor = new SysLogininforBo();
        logininfor.setTenantId(logininforEvent.getTenantId());
        logininfor.setUserName(logininforEvent.getUsername());
        if (ObjUtil.isNotNull(client)) {
            logininfor.setClientKey(client.getClientKey());
            logininfor.setDeviceType(client.getDeviceType());
        }
        logininfor.setIpaddr(ip);
        logininfor.setLoginLocation(address);
        logininfor.setBrowser(browser);
        logininfor.setOs(os);
        logininfor.setMsg(logininforEvent.getMessage());
        // 日志状态
        if (StringUtil.equalsAny(logininforEvent.getStatus(), Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            logininfor.setStatus(Constants.SUCCESS);
        } else if (Constants.LOGIN_FAIL.equals(logininforEvent.getStatus())) {
            logininfor.setStatus(Constants.FAIL);
        }
        // 插入数据
        insertLogininfor(logininfor);
    }

    private String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }

    @Override
    public PageResult<SysLogininforVo> selectPageLogininforList(SysLogininforBo logininfor, PageQuery pageQuery) {
        Map<String, Object> params = logininfor.getParams();
        QueryWrapper lqw = QueryWrapper.create().from(SYS_LOGININFOR)
                .where(SYS_LOGININFOR.IPADDR.like(logininfor.getIpaddr()))
                .and(SYS_LOGININFOR.STATUS.eq(logininfor.getStatus()))
                .and(SYS_LOGININFOR.USER_NAME.like(logininfor.getUserName()))
                .and(SYS_LOGININFOR.LOGIN_TIME.between(params.get("beginTime" ), params.get("endTime" ), params.get("beginTime" ) != null && params.get("endTime" ) != null));
        if (StringUtil.isBlank(pageQuery.getOrderByColumn())) {
            lqw.orderBy(SYS_LOGININFOR.INFO_ID, false);
        } else {
            lqw.orderBy(pageQuery.buildOrderBy());
        }
        Page<SysLogininforVo> page = baseMapper.paginateAs(pageQuery.build(), lqw, SysLogininforVo.class);
        return PageResult.build(page);
    }

    /**
     * 新增系统登录日志
     *
     * @param bo 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininforBo bo) {
        SysLogininfor logininfor = MapstructUtil.convert(bo, SysLogininfor.class);
        logininfor.setLoginTime(new Date());
        baseMapper.insert(logininfor, true);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininforVo> selectLogininforList(SysLogininforBo logininfor) {
        Map<String, Object> params = logininfor.getParams();
        return baseMapper.selectListByQueryAs(QueryWrapper.create().from(SYS_LOGININFOR)
                .where(SYS_LOGININFOR.IPADDR.like(logininfor.getIpaddr()))
                .and(SYS_LOGININFOR.STATUS.eq(logininfor.getStatus()))
                .and(SYS_LOGININFOR.USER_NAME.like(logininfor.getUserName()))
                .and(SYS_LOGININFOR.LOGIN_TIME.between(params.get("beginTime" ), params.get("endTime" ), params.get("beginTime" ) != null && params.get("endTime" ) != null))
                .orderBy(SYS_LOGININFOR.INFO_ID, false), SysLogininforVo.class);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public int deleteLogininforByIds(Long[] infoIds) {
        return baseMapper.deleteBatchByIds(Arrays.asList(infoIds));
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {
        baseMapper.deleteByQuery(new QueryWrapper());
    }
}

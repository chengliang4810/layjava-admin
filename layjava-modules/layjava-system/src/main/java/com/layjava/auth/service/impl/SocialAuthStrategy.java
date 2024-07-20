package com.layjava.auth.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.layjava.auth.domain.vo.LoginVo;
import com.layjava.auth.service.AuthStrategy;
import com.layjava.auth.service.AuthStrategyService;
import com.layjava.auth.service.SysLoginService;
import com.layjava.common.core.domain.model.LoginUser;
import com.layjava.common.core.domain.model.SocialLoginBody;
import com.layjava.common.core.enums.UserStatus;
import com.layjava.common.core.exception.ServiceException;
import com.layjava.common.core.exception.user.UserException;
import com.layjava.common.core.utils.ValidatorUtils;
import com.layjava.common.satoken.utils.LoginHelper;
import com.layjava.common.social.config.properties.SocialProperties;
import com.layjava.common.social.utils.SocialUtils;
import com.layjava.system.domain.SysClient;
import com.layjava.system.domain.SysUser;
import com.layjava.system.domain.vo.SysSocialVo;
import com.layjava.system.domain.vo.SysUserVo;
import com.layjava.system.mapper.SysUserMapper;
import com.layjava.system.service.ISysSocialService;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;
import java.util.Optional;

import static com.layjava.system.domain.table.SysUserTableDef.SYS_USER;


/**
 * 第三方授权策略
 *
 * @author thiszhc is 三三
 */
@Slf4j
@Component("social" + AuthStrategy.BASE_NAME)
public class SocialAuthStrategy implements AuthStrategyService {

    @Inject
    private SocialProperties socialProperties;
    @Inject
    private ISysSocialService sysSocialService;
    @Db
    private SysUserMapper userMapper;
    @Inject
    private SysLoginService loginService;

    /**
     * 登录-第三方授权登录
     *
     * @param body   登录信息
     * @param client 客户端信息
     */
    @Override
    public LoginVo login(String body, SysClient client) {
        SocialLoginBody loginBody = JSONUtil.toBean(body, SocialLoginBody.class);
        ValidatorUtils.validate(loginBody);
        AuthResponse<AuthUser> response = SocialUtils.loginAuth(
                loginBody.getSource(), loginBody.getSocialCode(),
                loginBody.getSocialState(), socialProperties);
        if (!response.ok()) {
            throw new ServiceException(response.getMsg());
        }
        AuthUser authUserData = response.getData();

        List<SysSocialVo> list = sysSocialService.selectByAuthId(authUserData.getSource() + authUserData.getUuid());
        if (CollUtil.isEmpty(list)) {
            throw new ServiceException("你还没有绑定第三方账号，绑定后才可以登录！");
        }
        Optional<SysSocialVo> opt = list.stream().findAny();
        if (opt.isEmpty()) {
            throw new ServiceException("对不起，你没有权限登录当前租户！");
        }
        SysSocialVo social = opt.get();
        // 查找用户
        SysUserVo user = loadUser(social.getUserId());

        // 此处可根据登录用户的数据不同 自行创建 loginUser 属性不够用继承扩展就行了
        LoginUser loginUser = loginService.buildLoginUser(user);
        loginUser.setClientKey(client.getClientKey());
        loginUser.setDeviceType(client.getDeviceType());
        SaLoginModel model = new SaLoginModel();
        model.setDevice(client.getDeviceType());
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        model.setTimeout(client.getTimeout());
        model.setActiveTimeout(client.getActiveTimeout());
        model.setExtra(LoginHelper.CLIENT_KEY, client.getClientId());
        // 生成token
        LoginHelper.login(loginUser, model);

        LoginVo loginVo = new LoginVo();
        loginVo.setAccessToken(StpUtil.getTokenValue());
        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setClientId(client.getClientId());
        return loginVo;
    }

    private SysUserVo loadUser(Long userId) {
        SysUser user = userMapper.selectOneByQuery(
                QueryWrapper.create().from(SYS_USER)
                        .select(SYS_USER.USER_NAME, SYS_USER.STATUS)
                        .and(SYS_USER.USER_ID.eq(userId)));
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", "");
            throw new UserException("user.not.exists", "");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", "");
            throw new UserException("user.blocked", "");
        }
        return userMapper.selectUserByUserName(user.getUserName());
    }

}

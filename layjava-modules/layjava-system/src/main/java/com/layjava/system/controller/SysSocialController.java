package com.layjava.system.controller;


import com.layjava.common.core.domain.R;
import com.layjava.common.satoken.utils.LoginHelper;
import com.layjava.common.web.core.BaseController;
import com.layjava.system.domain.vo.SysSocialVo;
import com.layjava.system.service.ISysSocialService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;

import java.util.List;

/**
 * 社会化关系
 *
 * @author thiszhc
 * @date 2023-06-16
 */


@Controller
@Mapping("/system/social")
public class SysSocialController extends BaseController {

    @Inject
    private ISysSocialService socialUserService;

    /**
     * 查询社会化关系列表
     */
    @Mapping("/list")
    public R<List<SysSocialVo>> list() {
        return R.ok(socialUserService.queryListByUserId(LoginHelper.getUserId()));
    }

}

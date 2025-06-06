# jimuqu-admin

[![Jimuqu Admin](https://img.shields.io/badge/Jimuqu_Admin-1.0.0-success.svg)](https://gitee.com/chengliang4810/jimuqu-admin)
![GitHub License](https://img.shields.io/github/license/chengliang4810/jimuqu-admin)
[![Solon](https://img.shields.io/badge/Solon-3.0.6-blue.svg)]()
[![JDK-17](https://img.shields.io/badge/JDK-17-green.svg)]()
[![JDK-21](https://img.shields.io/badge/JDK-21-green.svg)]()
![GitHub last commit](https://img.shields.io/github/last-commit/chengliang4810/jimuqu-admin)
<br>
![GitHub Repo stars](https://img.shields.io/github/stars/chengliang4810/jimuqu-admin)
[![star](https://gitee.com/opensolon/jimuqu-admin/badge/star.svg?theme=dark)](https://gitee.com/opensolon/jimuqu-admin/stargazers)
[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/chengliang4810/jimuqu-admin)


#### 介绍
jimuqu-admin 是基于solon的管理系统，

> 项目代码、文档 均开源免费可商用 遵循开源协议在项目中保留开源协议文件即可<br>
活到老写到老 为兴趣而开源 为学习而开源 为让大家真正可以学到技术而开源

> 文档地址: [https://doc.jimuqu.com](https://doc.jimuqu.com)

> deepwiki文档地址: [https://deepwiki.com/chengliang4810/jimuqu-admin](https://deepwiki.com/chengliang4810/jimuqu-admin)

> 演示系统: [https://admin.jimuqu.com](https://admin.jimuqu.com)


#### 主要技术栈

| 技术名                  | 作用                                                                 | 特点                                                                 |
|-------------------------|----------------------------------------------------------------------|----------------------------------------------------------------------|
| **Solon**              | Java 轻量级应用框架（IoC容器、Web开发等）                            | 国产、高性能、低延时（类似 Spring Boot 但更轻量）                    |
| **Hutool**             | Java 工具库（集合/日期/IO/加密等工具）                               | 国产、简化代码、功能全面                                             |
| **Sa-Token**           | 权限认证框架（登录/鉴权/会话管理）                                   | 国产、轻量级、支持分布式会话                                         |
| **Xbatis**             | MyBatis 增强工具（简化 CRUD 操作）                                   | 兼容 MyBatis、动态 SQL 支持                                          |
| **AutoTable**          | 数据库表结构自动维护（根据实体类生成/更新表）                        | 国产、支持多数据库（MySQL/SQLite）                                   |
| **EasyExcel**          | Excel 导入导出工具                                                  | 阿里开源、避免 OOM（大文件处理）                                     |
| **JustAuth**           | 第三方登录集成（微信/钉钉/GitHub 等）                                | 国产、支持 20+ 平台                                                 |
| **ip2region**          | 离线 IP 地址定位                                                    | 数据本地化、毫秒级查询                                               |
| **MapStruct Plus**     | 对象转换工具（DTO/Entity 互转）                                     | 基于 MapStruct、零反射、高性能                                       |
| **Lombok**             | 代码简化（自动生成 getter/setter 等）                               | 减少样板代码                                                         |
| **HikariCP**           | JDBC 连接池                                                         | 高性能、轻量级（默认 Spring Boot 连接池）                            |
| **TransmittableThreadLocal** | 线程间上下文传递（线程池场景）                                      | 阿里开源、解决异步线程上下文丢失                                     |
| **SQLite**             | 嵌入式数据库                                                         | 轻量级、零配置、单文件存储                                           |
| **Beetl/Velocity**     | 模板引擎（代码生成/页面渲染）                                       | Beetl 国产高性能，Velocity 老牌稳定                                 |

## 功能列表

| 业务                                  | 功能说明 |
|-------------------------------------|------| 
|客户端管理 | 系统内对接的所有客户端管理 如: pc端、小程序端等<br>支持动态授权登录方式 如: 短信登录、密码登录等 支持动态控制token时效 |
| 用户管理   | 用户的管理配置 如:新增用户、分配用户所属部门、角色、岗位等      |
| 部门管理   | 配置系统组织机构（公司、部门、小组） 树结构展现支持数据权限      |
| 岗位管理   | 配置系统用户所属担任职务                        |
| 菜单管理   | 配置系统菜单、操作权限、按钮权限标识等                 |
| 角色管理   | 角色菜单权限分配、设置角色按机构进行数据范围权限划分          |
| 字典管理   | 对系统中经常使用的一些较为固定的数据进行维护              |
| 参数管理   | 对系统动态配置常用参数                         |
| 通知公告   | 系统通知公告信息发布维护                        |
| 操作日志   | 系统正常操作日志记录和查询 系统异常信息日志记录和查询         |
| 登录日志   | 系统登录日志记录查询包含登录异常                    |
| 文件管理   | 系统文件展示、上传、下载、删除等管理                  |
| 文件配置管理 | 系统文件上传、下载所需要的配置信息动态添加、修改、删除等管理      |
| 在线用户管理 | 已登录系统的在线用户信息监控与强制踢出操作               |
| 定时任务   | 运行报表、任务管理(添加、修改、删除)、日志管理、执行器管理等     |
| 代码生成   | 多数据源前后端代码的生成（java、html、xml、sql）支持CRUD下载 |
| 系统接口   | 根据业务代码自动生成相关的api接口文档                |


## 系统预览图

![img.png](docs/images/img_1.png)
![img.png](docs/images/img_2.png)
![img.png](docs/images/img_3.png)
![img.png](docs/images/img_4.png)
![img.png](docs/images/img_5.png)
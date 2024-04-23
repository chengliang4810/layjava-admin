package ${package.Service};

import ${package.Entity}.bo.${entity}Bo;
import ${package.Entity}.vo.${entity}Vo;
import com.layjava.common.mybatis.core.page.PageQuery;
import com.layjava.common.mybatis.core.page.PageResult;

import java.util.List;

/**
 *
 * ${table.comment!} 实现类
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.serviceName} {

    /**
     * 查询${table.comment!}列表
     *
     * @param userBo ${table.comment!}Bo
     * @return ${table.comment!}列表
     */
    List<${entity}Vo> get${entity}VoList(${entity}Bo ${table.entityPath}Bo);

    /**
     * 获取${table.comment!}分页列表
     *
     * @param userBo    ${table.comment!}Bo
     * @param pageQuery 分页查询条件
     * @return {@link List}<{@link ${entity}Vo}>
     */
    PageResult<${entity}Vo> get${entity}VoList(${entity}Bo ${table.entityPath}Bo, PageQuery pageQuery);

    /**
     * 通过id查询${table.comment!}Vo
     *
     * @param id ${table.comment!}id
     * @return {@link ${entity}Vo} ${table.comment!}
     */
    ${entity}Vo get${entity}VoById(Long id);

    /**
     * 保存${table.comment!}
     *
     * @param ${table.entityPath}Bo ${table.comment!}
     * @return {@link boolean} 是否新增成功
     */
    boolean save${entity}(${entity}Bo ${table.entityPath}Bo);

    /**
     * 根据id更新${table.comment!}
     *
     * @param ${table.entityPath}Bo ${table.comment!}
     * @return {@link boolean} 是否更新成功
     */
    boolean update${entity}ById(${entity}Bo ${table.entityPath}Bo);

    /**
     * 根据id删除${table.comment!}
     *
     * @param idList {table.comment!}id列表
     * @return {@link boolean} 是否删除成功
     */
    boolean delete${entity}ById(List<Long> idList);

}
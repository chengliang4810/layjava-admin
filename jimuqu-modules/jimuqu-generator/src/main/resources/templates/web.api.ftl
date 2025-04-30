import { request } from '@/service/http'

/**
 * 查询${table.comment!}列表
 * @returns ${table.comment!}列表
 */
export const get${entity}List = (params?: ${package.ModuleName?cap_first}.${entity}Query) => {
  return request.Get<Service.PageDataResult<${package.ModuleName?cap_first}.${entity}Vo>>('/${package.ModuleName}/${controllerMappingHyphen}/page/list', { params })
}

/**
 * 查询${table.comment!}对象
 * @returns ${table.comment!}对象
 */
export const get${entity} = (id: string | number) => {
  return request.Get<Service.ResponseResult<${package.ModuleName?cap_first}.${entity}Vo>>('/${package.ModuleName}/${controllerMappingHyphen}/' + id)
}

/**
 * 新增${table.comment!}
 */
export const add${entity} =  (data: ${package.ModuleName?cap_first}.${entity}Form) => {
  return request.Post<Service.ResponseResult<any>>('/${package.ModuleName}/${controllerMappingHyphen}', data)
}

/**
 * 更新${table.comment!}
 */
export const update${entity} = (data: ${package.ModuleName?cap_first}.${entity}Form) => {
  return request.Put<Service.ResponseResult<any>>('/${package.ModuleName}/${controllerMappingHyphen}', data)
}

/**
 * 删除${table.comment!}
 */
export const delete${entity} =  (id: string | number | Array<string | number>) => {
  return request.Delete<Service.ResponseResult<any>>('/${package.ModuleName}/${controllerMappingHyphen}/' + id)
}

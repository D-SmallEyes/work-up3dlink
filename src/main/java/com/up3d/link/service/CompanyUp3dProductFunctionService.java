package com.up3d.link.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.up3d.link.common.result.Result;
import com.up3d.link.pojo.entity.CompanyUp3dProductFunction;

import java.util.List;
import java.util.Map;

public interface CompanyUp3dProductFunctionService extends IService<CompanyUp3dProductFunction> {

    /**
     * 获取公司商品权限级别的所有信息
     * @return
     */
    List<CompanyUp3dProductFunction> getCompanyUp3dProductFunctionAndChild();

    /**
     * 构建册数数据
     * @return
     */
    List<Map<String, Object>> getResult();

    /**
     * 获取系统服务的时间
     * @param up3dProductId
     * @return
     */
    Result getCompanyUp3dProductFunctionDate(Integer up3dProductId );
}

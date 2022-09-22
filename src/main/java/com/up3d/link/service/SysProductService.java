package com.up3d.link.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.up3d.link.pojo.entity.SysProduct;

import java.util.List;

/**
 * @author dongxuanchen
 */
public interface SysProductService extends IService<SysProduct> {

    /**
     * 根据公司id获取公司名称
     * @param companyId
     * @return
     */
    String getCompanyNameById(Integer companyId);

    /**
     * 获取所有公司id
     * @return
     */
    List<Integer> getAllId();

    /**
     * 获取所有服务信息
     * @return
     */
    List<SysProduct> getAll();
}

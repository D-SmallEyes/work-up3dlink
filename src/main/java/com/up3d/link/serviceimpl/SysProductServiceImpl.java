package com.up3d.link.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.up3d.link.mapper.SysProductMapper;
import com.up3d.link.pojo.entity.SysProduct;
import com.up3d.link.service.SysProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-01  11:10
 */
@Service
public class SysProductServiceImpl extends ServiceImpl<SysProductMapper, SysProduct> implements SysProductService {

    @Resource
    private SysProductMapper sysProductMapper;

    /**
     * 根据公司id获取公司名称
     *
     * @param companyId
     * @return
     */
    @Override
    public String getCompanyNameById(Integer companyId) {

        SysProduct sysProduct = sysProductMapper.selectById(companyId);
        if (sysProduct != null && sysProduct.getId() != null){
            return sysProduct.getName();
        }
        return "输入争取公司名";
    }

    /**
     * 获取所有公司id
     *
     * @return
     */
    @Override
    public List<Integer> getAllId() {
        return sysProductMapper.getAllId();
    }

    /**
     * 获取所有服务信息
     *
     * @return
     */
    @Override
    public List<SysProduct> getAll() {
        return sysProductMapper.selectList(null);
    }
}

package com.up3d.link.controller;

import com.up3d.link.common.result.Result;
import com.up3d.link.pojo.entity.CompanyUp3dProductFunction;
import com.up3d.link.service.CompanyUp3dProductFunctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-07-28  14:34
 */
@Api(tags = "在线授权服务")
@RestController
@RequestMapping("/up3d/CompanyUp3dProductFunction")
public class CompanyUp3dProductFunctionController {

    @Resource
    private CompanyUp3dProductFunctionService companyUp3dProductFunctionService;

    @ApiOperation("功能数据查出")
    @GetMapping("/getChildAll")
    public List<CompanyUp3dProductFunction> getCompanyUp3dProductFunctionsAndChild(){
        return companyUp3dProductFunctionService.getCompanyUp3dProductFunctionAndChild();
    }

    @ApiOperation("功能数据成结果树的形式")
    @GetMapping("/getResultTree")
    public List<Map<String, Object>> getCompanyUp3dProduct(){
        return companyUp3dProductFunctionService.getResult();
    }


    @ApiOperation("功能数据最后包装类型")
    @PostMapping("/getResult")
    public Result getCompanyUp3dPFById(Integer up3dProductId){
        return companyUp3dProductFunctionService.getCompanyUp3dProductFunctionDate(up3dProductId);
    }
}

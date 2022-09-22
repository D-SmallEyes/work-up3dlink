package com.up3d.link.controller;

import com.up3d.link.common.result.Result;
import com.up3d.link.pojo.resp.MedicalRecordNumberResp;
import com.up3d.link.service.DrSaasMedicalRecordNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-09-22  15:10
 * @Description: 病历号管理
 */
@Api(tags = "病历号管理")
@RestController
@RequestMapping("/up3d/medicalRecordNumberController")
public class MedicalRecordNumberController {

    @Autowired
    private DrSaasMedicalRecordNumberService drSaasMedicalRecordNumberService;

    @ApiOperation("病历号回显")
    @GetMapping("/getMedicalRecordNumberInfo")
    public Result<MedicalRecordNumberResp> getMedicalRecordNumberInfo(){
        return drSaasMedicalRecordNumberService.getMedicalRecordNumberInfo();
    }

    @ApiOperation("生成病历号")
    @PostMapping("/createMedicalRecordNumber")
    public Result<String> createMedicalRecordNumber(){
        return null;
    }
}

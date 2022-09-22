package com.up3d.link.service;


import com.up3d.link.common.result.Result;
import com.up3d.link.pojo.req.CreateMedicalRecordNumberReq;
import com.up3d.link.pojo.resp.MedicalRecordNumberResp;

/**
 *
 * 病历号规则表服务
 * @author dongxuanchen
 * @description 杭州云甲科技
 * @date 2022/09/22
 */
public interface DrSaasMedicalRecordNumberService {


    /**
     * 获取病历号回显数据
     * @return
     */
    Result<MedicalRecordNumberResp> getMedicalRecordNumberInfo();

    /**
     * 生成病历号
     * @param recordNumberReq
     * @return
     */
    Result<String> createMedicalRecordNumber(CreateMedicalRecordNumberReq recordNumberReq);
}

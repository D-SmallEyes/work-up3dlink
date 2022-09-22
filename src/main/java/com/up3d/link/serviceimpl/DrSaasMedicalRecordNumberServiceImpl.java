package com.up3d.link.serviceimpl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.up3d.link.common.result.Result;
import com.up3d.link.common.util.DateUtils;
import com.up3d.link.mapper.DrSaasMedicalRecordNumberMapper;
import com.up3d.link.pojo.entity.DrSaasMedicalRecordNumber;
import com.up3d.link.pojo.req.CreateMedicalRecordNumberReq;
import com.up3d.link.pojo.resp.MedicalRecordNumberResp;
import com.up3d.link.service.DrSaasMedicalRecordNumberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 病历号规则表服务
 * @author dongxuanchen
 * @description 杭州云甲科技
 * @date 2022/09/22
 */
@Service
public class DrSaasMedicalRecordNumberServiceImpl implements DrSaasMedicalRecordNumberService {

    @Autowired
    private DrSaasMedicalRecordNumberMapper drSaasMedicalRecordNumberMapper;

    /**
     * 获取病历号回显数据
     *
     * @return
     */
    @Override
    public Result<MedicalRecordNumberResp> getMedicalRecordNumberInfo() {

        return null;
    }

    /**
     * 生成病历号
     *
     * @param recordNumberReq
     * @return
     */
    @Override
    public Result<String> createMedicalRecordNumber(CreateMedicalRecordNumberReq recordNumberReq) {
        //设置年月日
        int year = DateUtils.getYear(new Date()) % 100;
        int month = DateUtils.getMonth(new Date());
        String monthString = "";
        if (month < 10){
            monthString = "0"+month;
        }else {
            monthString = "" + month;
        }
        int day = DateUtils.getDay(new Date());
        //根据所传条件查询是否已经为已有病例号规则
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("company_id",0L);
        if (recordNumberReq.getPrefix() != null){
            queryWrapper.eq("prefix",recordNumberReq.getPrefix());
        }
        queryWrapper.eq("type",recordNumberReq.getType());
        if (recordNumberReq.getType() == 1){
            queryWrapper.eq("year",year+"");
            queryWrapper.eq("month",monthString);
        }
        if (recordNumberReq.getType() ==2 || recordNumberReq.getType() ==3){
            queryWrapper.eq("day",day+"");
        }
        DrSaasMedicalRecordNumber drSaasMedicalRecordNumber = drSaasMedicalRecordNumberMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(drSaasMedicalRecordNumber)){
            //新建病历号规则 并生成病历号
        }else{
            //已存在病例规则 生成病历号即可
        }
        DrSaasMedicalRecordNumber medicalRecordNumber = new DrSaasMedicalRecordNumber();
        medicalRecordNumber.setCompanyId(0L);
        medicalRecordNumber.setGmtCreate(DateUtils.nowByStamp());
        medicalRecordNumber.setType(recordNumberReq.getType());
        medicalRecordNumber.setYear(year+"");
        medicalRecordNumber.setMonth(monthString);

        //设置下一个流水值
        Integer nextValue = recordNumberReq.getNextValue();
        //设置流水号
        String serialNumberString = "";
        String medicalRecordNumberStr = "" ;
        switch (recordNumberReq.getType()){
            case 1:
                //年月+3位流水号 超出直接加
                if (nextValue < 10) {
                    serialNumberString = "00" + (nextValue++);
                } else if (nextValue < 100) {
                    serialNumberString = "0" + (nextValue++);
                } else {
                    serialNumberString = (nextValue++) + "";
                }
                //设置前缀
                if (recordNumberReq.getPrefix() != null){
                    medicalRecordNumber.setPrefix(recordNumberReq.getPrefix());
                    medicalRecordNumberStr = recordNumberReq.getPrefix()+year+monthString+serialNumberString;
                }else{
                    medicalRecordNumberStr = "" + year + monthString+ serialNumberString;
                }
                break;
            case 2:
                //年月日+2位流水号
                if (nextValue < 10) {
                    serialNumberString = "0" + (nextValue++);
                }else{
                    serialNumberString = (nextValue++) + "";
                }
                //设置日期
                medicalRecordNumber.setDay(day+"");
                //设置前缀
                if (recordNumberReq.getPrefix() != null){
                    medicalRecordNumber.setPrefix(recordNumberReq.getPrefix());
                    medicalRecordNumberStr = recordNumberReq.getPrefix()+year+monthString+day+serialNumberString;
                }else{
                    medicalRecordNumberStr = "" + year + monthString+day+ serialNumberString;
                }

        }
        medicalRecordNumber.setSerialNumber(serialNumberString);
        medicalRecordNumber.setNext(nextValue);
        //设置要生成的病历号
        medicalRecordNumber.setMedicalRecordNumber(medicalRecordNumberStr);
        //设置是否为作为系统配置
        medicalRecordNumber.setIsSys(true);
        boolean b = drSaasMedicalRecordNumberMapper.insert(medicalRecordNumber) > 0;

        return Result.success(medicalRecordNumberStr);
    }



    private String setSerialNumber(Integer code , Integer next, String prefix) {

        String SerialNumberString = null;
        switch (code) {
            case 1:
                if (next < 10) {
                    SerialNumberString = "00" + (next + 1);
                } else if (next < 100) {
                    SerialNumberString = "0" + (next + 1);
                } else {
                    SerialNumberString = (next + 1) + "";
                }
                break;
            case 2:
                if (next < 10) {
                    SerialNumberString = "0" + (next + 1);
                }
                if (next >= 10) {
                    SerialNumberString = (next + 1) + "";
                }
                break;
            case 3:
                if (next < 10) {
                    SerialNumberString = "00" + (next + 1);
                } else if (next < 100) {
                    SerialNumberString = "0" + (next + 1);
                } else {
                    SerialNumberString = (next + 1) + "";
                }
                break;
            case 4:
                if (next < 10) {
                    SerialNumberString = "00000" + (next + 1);
                } else if (next < 100) {
                    SerialNumberString = "0000" + (next + 1);
                } else if (next < 1000) {
                    SerialNumberString = "000" + (next + 1);
                } else if (next < 10000) {
                    SerialNumberString = "00" + (next + 1);
                } else if (next < 100000) {
                    SerialNumberString = "0" + (next + 1);
                } else {
                    SerialNumberString = (next + 1) + "";
                }
                break;
            case 5:
                //生成临时病历号
                SerialNumberString = null;
                break;
            default:
                throw new RuntimeException("请选择正确的规则");
        }

        return null;
    }
}
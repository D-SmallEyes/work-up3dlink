package com.up3d.link;

import com.up3d.link.common.result.Result;
import com.up3d.link.pojo.req.CreateMedicalRecordNumberReq;
import com.up3d.link.service.DrSaasMedicalRecordNumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-09-22  17:04
 * @Description: 接口测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private DrSaasMedicalRecordNumberService drSaasMedicalRecordNumberService;

    @Test
    public void test0(){
        CreateMedicalRecordNumberReq createMedicalRecordNumberReq=  new CreateMedicalRecordNumberReq();
        createMedicalRecordNumberReq.setNextValue(1);
//        createMedicalRecordNumberReq.setPrefix("DW");
        createMedicalRecordNumberReq.setType(2);

        Result medicalRecordNumber = drSaasMedicalRecordNumberService.createMedicalRecordNumber(createMedicalRecordNumberReq);
        System.out.println(medicalRecordNumber.getData());
    }
}

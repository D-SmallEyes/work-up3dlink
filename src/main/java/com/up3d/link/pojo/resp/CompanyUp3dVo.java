package com.up3d.link.pojo.resp;

import lombok.Data;

import java.util.List;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-07-28  16:55
 * @Description: 本地授权响应
 */
@Data
public class CompanyUp3dVo {

    private String key;
    private String startTime;
    private String endTime;

    private List<CompanyUp3dVo> companyVoList;
}

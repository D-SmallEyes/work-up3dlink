package com.up3d.link.common.Enum;
/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-07-29  15:10
 * @Description: 1正常，2过期，3禁用
 */
public enum CompanyUp3dProductFunctionEnum {

    /**
     * 1正常
     */
    NORMAL(1),

    /**
     * 2过期
     */
    OVERDUE(2),

    /**
     * 3禁用
     */
    FORBIDDEN(3);


    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    CompanyUp3dProductFunctionEnum(Integer status) {
        this.status = status;
    }
}

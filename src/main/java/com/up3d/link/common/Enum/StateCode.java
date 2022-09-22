package com.up3d.link.common.Enum;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-09-22  16:09
 */
public interface StateCode {
    int SUCCESS = 200;
    /**
     * 500 用户无感知的错误码
     */
    int FAIL = 500;
    /**
     * 1000-5000 用户感知状态码
     */
    int FAIL_NEED_SHOW = 1000;

    int getCode();

    String getMsg();
}

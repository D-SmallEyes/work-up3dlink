package com.up3d.link.common.Enum;

/**
 *
 */
public enum StateCodeEnum {

    /**
     * 请求失败
     */
    FAIL(500, "请求失败"),
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),
    /**
     * 订单已接收
     */
    ORDER_ALREADY_RECEIVE(601, "订单不是待接收状态"),
    /**
     * 强制刷新页面状态码
     */
    REFRESH_PAGE_BY_FORCE(555, "强制刷新页面状态码"),
    /**
     * 建议刷新页面状态码
     */
    REFRESH_PAGE_BY_SUGGEST(556, "建议刷新页面"),
    /**
     * 强制接口刷新状态码
     */
    REFRESH_INTERFACE_BY_FORCE(560, "强制刷新接口"),

    /**
     * 前端重新登录
     */
    RE_LOGIN(505, ""),
    /**
     * 后端重新登录
     */
    RE_PLATFORM_LOGIN(506, ""),

    WX_NO_REGESERT(521, "不存在这个用户请调用注册接口"),



    /**
     * 对不起，您暂未到达领取本任务的等级，请继续努力哟！
     */
    TECHNICIAN_TITLE_LIMIT(167308, ""),

    /**
     * 有接单员且流程处于已开始或者已完成时报错
     */
    OPERATION_FAILED(700, ""),

    /**
     * 该任务正在被人操作，无法操作
     */
    BE_MANIPULATED(701, ""),

    FILE_TOO_MUCH(801, "文件过多"),


    NO_MODEL(802, "没有模型"),

    DUPLICATE_NAME(902, "名称重复"),

    THREESHAPE_ORDERNUM_ERR(1000, "订单编号错误"),

    THREESHAPE_FILETEETH_ERR(1001, "牙位文件信息缺失"),

    THREESHAPE_FILETEETH_NUM_ERR(1002, "牙位文件信息与实际需要的数量不一致"),

    THREESHAPE_FILETEETH_TOOTH_ERR(1003, "牙位文件信息,牙位信息错误，不存在该牙位"),

    THREESHAPE_FILETEETHNAME_ERR(1004, "牙位文件名重复"),

    THREESHAPE_ORDERNUM_STAFF_FUNCTION_NULL_ERR(1005, "职能或者员工id为空"),

    THREESHAPE_ORDERNUM_STAFF_MISMATCH_ERR(1006, "员工id不匹配"),

    THREESHAPE_ORDERNUM_FUNCTION_MISMATCH_ERR(1007, "职能id不匹配，订单当前流程不在设计"),

    CAM_CONTINUE_EQUIPMENT_ERR(2000, "当前加工机不属于当前企业，重选加工机"),
    ;
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    StateCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
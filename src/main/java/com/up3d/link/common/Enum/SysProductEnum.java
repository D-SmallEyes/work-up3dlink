package com.up3d.link.common.Enum;

/**
 * @author dxc
 * 系统_服务商品名
 */
public enum SysProductEnum {

    SCANNER(1,"SCANNER"),
    UPCAD(2,"UPCAD"),
    UPCAM(3,"UPCAM"),
    CAM(3,"CAM"),
    MODELEDITOR(4,"MODELEDITOR"),
    ZHENGQI(5,"正畸"),
    UPSTUDIO(6,"UPSTUDIO"),
    UPCNC(7,"UPCNC"),
    UP3DLINK(8,"UP3DLINK"),
    UPViewer(9,"UPViewer"),
    CAD_CAM(11,"CAD&CAM"),
    INTRAORAL(21,"Intraoral");

    private  int code;

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

    public static String getMsgByCode(Integer code){
        for (SysProductEnum value : values()) {
            if (value.getCode() == code){
                return value.getMsg();
            }
        }
        return null;
    }

    public static Integer getCodeByMsg(String msg){
        for (SysProductEnum value : values()) {
            if (value.getMsg().equals(msg)){
                return value.getCode();
            }
        }
        return null;
    }

    /**
     * 获取系统信息名
     */
    SysProductEnum(int code , String msg){
        this.code = code;
        this.msg = msg;
    }

}

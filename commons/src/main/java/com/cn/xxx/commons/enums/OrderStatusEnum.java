package com.cn.xxx.commons.enums;

public enum OrderStatusEnum {

    WAIT_PAY("WAIT_PAY", "等待支付"),//未支付
    PAY_FAILURE("PAY_FAILURE", "支付失败"),//异常订单
    NOTIFY_TIME_OUT("NOTIFY_TIME_OUT", "支付回调超时"),
    CANCEL_ORDER("CANCEL_ORDER", "订单取消"),
    FINISH("FINISH", "订单完成");//已支付

    private final String code;//简码
    private final String description;//描述

    private OrderStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatusEnum getByCode(String code) {
        for (OrderStatusEnum status : values()) {
            if (status.getCode().equalsIgnoreCase(code)) {
                return status;
            }
        }
        return null;
    }
}

/**
 * 
 */
package com.cn.xxx.commons.core;

/**
 * @Discription: 订单结果码枚举
 * @ClassName: OrderResultCodeEnum
 *
 * @Author:[caiwei]
 * @Version 1.0
 * @Date 2014年9月22日 下午6:20:35
 *
 */
public enum ResultCode {

    //========================================================================//
    //                            公共定义错误码                                                                //
    //========================================================================//

    /** 处理成功 */
    SUCCESS("COMMON_RS_100000200", "success"),

    /** 未知异常 */
    UN_KNOWN_EXCEPTION("COMMON_RS_520000500", "unknown exception"),

    /** 参数不合法 */
    PARAM_ILLEGAL("COMMON_RS_510000400", "request param illegal"),

    /** 重复请求 */
    REPEAT_REQUEST("COMMON_RS_510000401", "repeat request"),

    /** FAILOVER */
    FAILOVER_ERROR("COMMON_RS_510000402", "request occur failover"),
    
    //========================================================================//
    //                            领域公用错误码                                                                //
    //========================================================================//
    /** 订单状态非法 */
    ORDER_STATUS_ILLEGAL("ORDER_RS_510001201", "order status illegal"),
    
    /** 订单金额不能为空 */
    ORDER_AMOUNT_IS_NULL("ORDER_RS_510001805", "order amount should not be null"),
    
    /** 订单金额不能为空 */
    ORDER_AMOUNT_IS_ZERO("ORDER_RS_510001806", "order amount should not be zero"),
    
    //========================================================================//
    //                            订单创建错误码                                                                //
    //========================================================================//
    
    //========================================================================//
    //                            支付创建错误码                                                                //
    //========================================================================//

    //========================================================================//
    //                            支付回执错误码                                                                //
    //========================================================================//
    /** 支付回执金额和交易支付单金额不一致 */
    RECEIPT_BILL_AMOUNT_NOT_EQUAL("PAYMENT_RS_510001109",
                                  "receipt bill's amount not equals with paybill's"),
    ALIPAY_NOTIFY_VERITY_FAILED("PAYMENT_RS_510001110", "sign or notify id illegal"),
    
    //========================================================================//
    //                              退票错误码                                                                   //
    //========================================================================//
    REFUND_AMOUNT_EXCEED_LIMIT("PAYMENT_RS_510001808", "refund amount exceed limit"),
    
    /** 退票单状态非法 */
    REFUND_STATUS_ILLEGAL("PAYMENT_RS_510001809", "refund status illegal"),
    
    /** 重复退票 */
    REPEAT_REFUND("PAYMENT_RS_510001810", "repeat refund"),
    
    /** 不支持的退票方式 */
    REFUND_NOT_SUPPORT("REFUND_RS_510001813", "refund not support"),
    
    /** 退成人时将携带的婴儿一并退票 */
    BABY_CAN_NOT_ALONE("REFUND_RS_510001822", "baby can not alone"),
    
    /** 退成人时将携带的婴儿一并退票 */
    BABY_AND_BABY_CAN_NOT_ALONE("REFUND_RS_510001823", "child and baby can not alone"),    
    
    //========================================================================//
    //                              差错支付错误码                                                                   //
    //========================================================================//
    /** 订单状态非法 */
    PAYMENT_MISTAKE_STATUS_ILLEGAL("PAYMENT_RS_510001811", "payment mistake status illegal"),
	;
	
	/** 简码 */
    private final String code;

    /** 描述 */
    private final String description;

    /**
     * 私有构造方法
     *
     * @param code          简码
     * @param description   描述
     */
    private ResultCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * 通过枚举<code>code</code>获得枚举。
     *
     * @param code  简码
     * @return      枚举
     */
    public static ResultCode getByCode(String code) {
        for (ResultCode status : values()) {
            if (status.getCode().equalsIgnoreCase(code)) {
                return status;
            }
        }
        return null;
    }
}

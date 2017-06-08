package com.cn.xxx.yhsscore.util;

import org.joda.time.DateTime;

public class OrderUtil {
	public static int LEVEL1 = 1 ;
	public static int LEVEL2 = 2 ;
	public static String MIAOSHA = "MS";
	public static String TUANGOU = "TG";
	
	public static String createOrderNO(int level,String type,String uuid){
		if(uuid == null){
			uuid = UUIDUtil.getUUID(6, UUIDUtil.NUMBER);
		}
		String orderNO = "";
		String timeStr = DateTime.now().toString("yyyyMMddHHmmss");
		if(LEVEL1 == level){//大订单号
			orderNO = timeStr + uuid ;
		}else if(MIAOSHA.equals(type)){
			orderNO = MIAOSHA + timeStr + uuid ;
		}else if(TUANGOU.equals(type)){
			orderNO = TUANGOU + timeStr + uuid ;
		}else{
			orderNO = "WZ" + timeStr + uuid ;
		}
		return orderNO;
	}

	public static void main(String[] args) {
		System.out.println(DateTime.now().toString("yyyyMMddHHmmss"));

	}

}

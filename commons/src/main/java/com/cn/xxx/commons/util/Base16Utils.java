package com.cn.xxx.commons.util;

import java.io.UnsupportedEncodingException;

public class Base16Utils {

	public static String hex2Str(String theHex) throws UnsupportedEncodingException {
		byte[] theByte = new byte[theHex.length() / 2];

		for (int i = 0; i < theHex.length(); i += 2) {
			theByte[i / 2] = Integer.decode("0X" + theHex.substring(i, i + 2))
					.byteValue();
		}
		return new String(theByte, "UTF-8");
	}

	public static String str2Hex(String theStr) {
		byte[] bytes;
		int tmp;
		String tmpStr;
		bytes = theStr.getBytes();
		StringBuffer result = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			tmp = bytes[i];
			if (tmp < 0) {
				tmp += 256;
			}

			tmpStr = Integer.toHexString(tmp);
			if (tmpStr.length() == 1) {
				result.append("0");
			}

			result.append(tmpStr);
		}

		return result.toString();
	}

	public static void main(String[] argv) throws UnsupportedEncodingException {
		String a = "你好啊";
		String b = str2Hex(a);
		String c = hex2Str(b);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}
}
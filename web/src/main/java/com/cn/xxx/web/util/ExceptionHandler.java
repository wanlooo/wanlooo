package com.cn.xxx.web.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cn.xxx.commons.util.JsonUtil;

public class ExceptionHandler implements HandlerExceptionResolver {
	
	@Override
	public ModelAndView resolveException(HttpServletRequest resq,
			HttpServletResponse resp, Object handler, Exception ex) {
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = null ;
		Map<String,Object> ret = new HashMap<String, Object>();
		ret.put("success", false);
		ret.put("reason", "未知错误请联系网站维护人员！");
		if(ex.getMessage() != null && !ex.getMessage().trim().equals("")){
			ret.put("reason", ex.getMessage());
		}
		try {
			pw = resp.getWriter();
			pw.write(JsonUtil.writeValueAsString(ret));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.close();
		return null;
	}

}

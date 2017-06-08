package com.cn.xxx.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.wxcore.util.SignUtil;
import com.cn.xxx.yhsscore.service.CoreService;

@Controller
@Scope("prototype")
@RequestMapping("/wechat/core")
public class CoreController{
	
	private Logger logger = LoggerFactory.getLogger(CoreController.class);
	@Autowired
	private CoreService coreService ;
	
	/**
	 * 请求校验（确认请求来自微信服务器）
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.GET)
	public void doGet(@RequestParam(value = "signature", required = true) String signature,
			          @RequestParam(value = "timestamp", required = true) String timestamp,
			          @RequestParam(value = "nonce", required = true) String nonce,
			          @RequestParam(value = "echostr", required = true) String echostr,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		logger.info("微信认证signature："+signature);
		logger.info("微信认证timestamp："+timestamp);
		logger.info("微信认证nonce："+nonce);
		logger.info("微信认证echostr："+echostr);
		
		PrintWriter out = response.getWriter();
		
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
//			return echostr;
		}
		out.close();
		out = null;
//		return null;
	}
	
	/**
	 * 处理微信服务器发来的消息
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
//	public String doPost(@RequestBody String requestXml) throws IOException {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// 调用核心业务类接收消息、处理消息
		String respXml = null;
//		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		try {
			//读取body体内容
			int len = request.getContentLength(); 
			ServletInputStream sis = request.getInputStream();
			byte[] buffer = new byte[len];
			sis.read(buffer, 0, len);
			String requestXml = new String(buffer);
			
			respXml = coreService.processRequest(requestXml);
		} catch (IOException e) {
			e.printStackTrace();
			respXml = "success";
		}
//		respXml = new String(respXml.getBytes(),"UTF-8");
		out.print(respXml);
		out.close();
//		return respXml;
	}
}
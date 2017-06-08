package com.cn.xxx.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
@RequestMapping("/test")
public class TestController {

	@ResponseBody
	@RequestMapping(value="/test1",method=RequestMethod.GET)
	public Map<String,Object> test(){
		System.out.println("in test controller");
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", true);
//		List<User> list = this.userDao.queryUser();
//		result.put("users", list);
		
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/testException",method=RequestMethod.GET)
	public Map<String,Object> test(Integer t) throws Exception{
		System.out.println("in testException controller --- "+t);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", true);
		if(t==1){
			throw new Exception("testException");
		}
		
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/testImage",method=RequestMethod.GET)
	public void testImage(HttpServletRequest request,HttpServletResponse response,String ticket) throws Exception{
		
		ByteArrayOutputStream baos = null;
		URL u = new URL("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket);
		BufferedImage image = ImageIO.read(u);

		baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		baos.flush();
		byte[] pbyte = baos.toByteArray();
		
		 response.setHeader("Content-Type","image/jpg");
		 response.getOutputStream().write(pbyte);
	}
}

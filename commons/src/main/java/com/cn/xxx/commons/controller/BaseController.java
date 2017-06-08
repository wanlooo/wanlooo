package com.cn.xxx.commons.controller;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cn.xxx.commons.core.Pagination;


/**
 * @author xulong
 */
@SuppressWarnings("all")
public class BaseController {
	
	private static final String messagePath = "message.";
	private static final String messagePrefix = "message";
	
	protected HttpServletRequest request;
    protected HttpServletResponse response;  
    protected HttpSession session; 
    protected ResourceBundle resourceBundle;
    protected Locale locale = null;
    
    /**
     * 获取HttpServletRequest、HttpServletResponse对象
     * @param request
     * @param response
     */
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();
        
    }
    
    /**
     * 将异常或者错误信息发送到前台
     * @param errorMsg
     * @throws Exception
     */
    public void renderError(String errorMsg) throws Exception {
		response.setCharacterEncoding("UTF-8");
    	String json = "{\"success\":\"false\", \"msg\":\""+errorMsg+"\"}";
		response.getWriter().println(json);	
	}
    
	/**
	 * 渲染List数据,返回格式: [{dept:'部门二',"id":"121","user":"张三"},{"dept:'部门二',"id":"122","user":"李四"}]
	 * @param list
	 * @throws Exception
	 */
//	public void renderJsonForList(List<?> list) throws Exception {
//		this.render(getJson(list), "application/json", "utf-8");
//	}
	
	/**
	 * 渲染单个对象,返回格式: {success:true, data:{dept:"部门","userName":"张三"}}
	 * @param object 单个实体对象
	 * @throws Exception
	 */
//	public void renderJson(Object object) throws Exception {
//		String result = "{\"success\":\"true\",\"data\":";
//		result = result + getJson(object) + "}";
//		this.render(result, "application/json", "utf-8");
//	}
	
	protected void render(String text, String contentType, String characerEncoding)throws Exception {
    	response.setCharacterEncoding(characerEncoding);
    	this.renderToFront(text, contentType);
    }
	
	/**
	 * 将json格式数据发送到前端
	 * @param text
	 * @param contentType
	 * @throws Exception
	 */
	protected void renderToFront(String text, String contentType) throws Exception {
		response.setContentType(contentType);
		response.getWriter().println(text);
    }
	
	/*protected String getJson(Object object) {
	    JsonConfig config = new JsonConfig();  
        //config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
	    
        //防止数值类型为null返回0
        config.registerDefaultValueProcessor(Long.class,  new DefaultValueProcessor() {     
            public Object getDefaultValue(Class type) {     
                return null;     
            }     
        });
        config.registerDefaultValueProcessor(Double.class,  new DefaultValueProcessor() {     
            public Object getDefaultValue(Class type) {     
                return null;     
            }     
        });
        
        config.registerDefaultValueProcessor(Integer.class,  new DefaultValueProcessor() {     
            public Object getDefaultValue(Class type) {     
                return null;     
            }     
        });
        JSONObject json = JSONObject.fromObject(object, config);
        return json.toString();
	}
	
	protected String getJson(List<?> list) {
		JsonConfig config = new JsonConfig();  
		
		//config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss")); 
		config.registerDefaultValueProcessor(Long.class,  new DefaultValueProcessor() {     
            public Object getDefaultValue(Class type) {     
                return null;     
            }     
        });
        config.registerDefaultValueProcessor(Double.class,  new DefaultValueProcessor() {     
            public Object getDefaultValue(Class type) {     
                return null;     
            }     
        });
        
        config.registerDefaultValueProcessor(Integer.class,  new DefaultValueProcessor() {     
            public Object getDefaultValue(Class type) {     
                return null;     
            }     
        });
		JSONArray json = JSONArray.fromObject(list,config);
		return json.toString();
		
	}*/
	
	/***********分页*************/
	
	private int start = 0;//第几条
	private int length = 10;//每一页的默认查询条数
	
	protected Pagination pagination = new Pagination(0);
	
	public Pagination getPagination() {
		pagination.setPageSize(getLength());
		pagination.setStartIndex(getStart());
		return pagination;
	}
	
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public int getStart() {
		String startString = request.getParameter("start");
		if(!StringUtils.isEmpty(startString)){
			return Integer.parseInt(startString);
		}
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		String lengthString = request.getParameter("length");
		if(!StringUtils.isEmpty(lengthString)){
			return Integer.parseInt(lengthString);
		}
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	
	/**
	 * 生成json格式的分页结果
	 * @param objects 对象列表
	 * @throws Exception
	 */
	/*public void renderJsonPage(List<?> objects) throws Exception {
		this.render(generatePaginationJsonString(objects), "application/json", "utf-8");
	}*/
	
    /**
     * @param objects 对象列表
     * @return 结果：{totalCount:100,result:[{"userName":"用户1","userId":"","userName":"user1"},{"userName":"用户2","userId":"","userName":"user2"}]}
     * @throws Exception
     */
	/*private String generatePaginationJsonString(List<?> objects) throws Exception  {
		String json = "{\"total\":"+this.getPagination().getTotalCount()
				+",\"currPage\":"
				+this.getPagination().getCurrentPage()
				+",\"pageSize\":"
				+this.getPagination().getPageSize()
				+ ", \"data\":"
				+ this.getJson(objects)
				+ "}";
		return json;
	}*/
	
}


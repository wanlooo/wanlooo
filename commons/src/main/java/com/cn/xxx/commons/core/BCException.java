
package com.cn.xxx.commons.core;


public class BCException extends RuntimeException {
	
	private static final long serialVersionUID = 5875000400471599623L;
	
	public static final BCException NO_TOKEN = new BCException("-10004", "请登录");
	
	public static final BCException INVALID_USER = new BCException("-20009", "账户或密码错误！");
	
	public static final BCException INVALID_CAPTCHA = new BCException("-20009", "验证码错误！");
	
	public static final BCException INVALID_MOBILEPASS = new BCException("-20009", "口令与手机不匹配！");
	
	public static final BCException INVALID_MOBILEPASS_EXPIRED = new BCException("-20009", "密码已过期请重新获取！");
	
	public static final BCException UNRECOGNIZED_REQUEST = new BCException("-20010", "无法识别的请求内容！");
	
	public static final BCException UNKNOWN_EXCEPTION = new BCException("-29998", "未知错误！请联系系统管理员！");
	
	public static final BCException NO_ENTRY_SELECTED = new BCException("-30000", "未指定数据项?");
	
	public static final BCException UPDATE_FAILED = new BCException("-30000", "数据更新失败?");
	
	public static final BCException NOT_EXISTED_ROLE = new BCException("-30000", "角色不存在！");
	
	public static final BCException NO_ACCESS_ROLE = new BCException("-30000", "无访问权限的角色?");
	
	public static final BCException NO_ROLE = new BCException("-30000", "该用户无可用角色?");
	
	public static final BCException NO_CORE = new BCException("-30000", "未指定Solr实例?");
	
	public static final BCException NO_DATA_OBJECT = new BCException("-30000", "未指定实体对象！");
	
	public static final BCException NO_MATCHABLE_OBJECT = new BCException("-30000", "无匹配的实体对象?");
	
	public static final BCException NO_FILE = new BCException("-30000", "文件不存在！");
	
	public static final BCException NO_KEY = new BCException("-30000", "KEY值不存在?");
	
	public static final BCException TASK_NOT_EXIST = new BCException("-30000", "指定的任务不存在?");
	
	public static final BCException JUNK_DATA = new BCException("-30000", "存在垃圾数据?");
	
	public static final BCException PATH_NOT_EXIST = new BCException("-30000", "系统找不到指定的路径?");
	
	public static final BCException RESUBMIT = new BCException("-30000", "请勿重复提交?");
	
	public static final BCException NO_ATTACHMENTS = new BCException("-30000", "该对象无附件功能?");
	
	public static final BCException MISSING_PARAMETERS = new BCException("-30000", "缺少参数?");
	
	public static final BCException NUM_REPEAT = new BCException("-30000", "编号重复?");
	
	public static final BCException NO_PLUGIN = new BCException("-30000", "插件不存在");
	
	public static final BCException NO_DUPLICATE = new BCException("-30000", "记录重复!");
	
	public static final BCException USER_DUPLICATE = new BCException("-30000", "用户已被注册，请更换用户名!");
	
	public static final BCException USERCODE_DUPLICATE = new BCException("-30000", "昵称已被注册，请更换昵称再试!");
	
	public static final BCException USER_CARDID_DUPLICATE = new BCException("-30000", "证件已被注册，请更换证件再试!");
	
	public static final BCException MOBILE_DUPLICATE = new BCException("-30000", "手机已被注册，请更换手机再试!");
	
	public static final BCException EMAIL_DUPLICATE = new BCException("-30000", "邮箱已被注册，请更换邮箱再试!");
	
	public static final BCException ROLE_DUPLICATE = new BCException("-30000", "角色已存在，请更换角色名!");
	
	public static final BCException PERMPOINT_DUPLICATE = new BCException("-30000", "权限点已存在，请更换权限点名!");
	
	public static final BCException NULL_VALUE = new BCException("-30000", "%s不能为空!");
	
	public static final BCException FORMAT_ERROR = new BCException("-30000", "%s格式错误!");
	
	public static final BCException INVALID_USERCODE = new BCException("-30000", "用户名只能是4-15位字母、数字、减号和下划线");
	
	public static final BCException NO_FLIGHT_FARE = new BCException("-30000", "无航班运价信息！");
	
	public static final BCException AIRPORT_ERROR = new BCException("-30000", "机场三字码错误！");
	
	public static final BCException FLIGHT_REPEAT = new BCException("-30000", "航班重复错误！");

	public static final BCException BASEFEE_ERROR = new BCException("-30000", "基础运价格式错误！");

	public static final BCException MILEAGE_ERROR = new BCException("-30000", "里程格式错误！");

	public static final BCException PERCENT_ERROR = new BCException("-30000", "折扣率格式错误！");

	public static final BCException DATE_PARSE_ERROR = new BCException("-30000", "日期格式错误！");

	public static final BCException BASE_FEE_TOO_LONG = new BCException("-30000", "基础运价过长！");
	
	public static final BCException MILEAGE_TOO_LONG = new BCException("-30000", "里程过长！");
	
	public static final BCException FARE_BASE_TOO_LONG = new BCException("-30000", "运价基础过长！");

	public static final BCException FARE_BASE_ERROR = new BCException("-30000", "运价基础格式错误，长度为9位字符，可包括字母和数字，不允许中文！");
	
	public static final BCException ADULTFEE_TOO_LONG = new BCException("-30000", "成人机建费过长！");
	
	public static final BCException ADULTFEE_ERROR = new BCException("-30000", "成人机建费格式错误！");
	
	public static final BCException CHILDFEE_TOO_LONG = new BCException("-30000", "儿童机建费过长！");
	
	public static final BCException CHILDFEE_ERROR = new BCException("-30000", "儿童机建费格式错误！");
	
	public static final BCException ICS_EXCEPTION = new BCException("-30000", "%s");
	
	public static final BCException TOUR_CODE_TOO_LONG = new BCException("-30000", "旅行代码过长！");
	
	public static final BCException TOUR_CODE_ERROR = new BCException("-30000", "旅行代码格式错误，长度为9位字符，可包括字母和数字，不允许中文！");
	
	public static final BCException REGISTER_NOTE_TOO_LONG = new BCException("-30000", "签注项过长！");
	
	public static final BCException TITLE_ERROR = new BCException("-30000", "标题不正确！");
	
	public static final BCException SPECIAL_CHAR = new BCException("-20004", "请求内容含有特殊字符，请检查后重试");
	
	public static final BCException FILE_UPLOAD_SIZE = new BCException("-30000", "上传文件必须小于%s");
	
	public static final BCException FILE_UPLOAD_FORMAT_ERROR = new BCException("-30000", "文件名必须为英文或数字。");
	
	public static final BCException FILE_IMAGE = new BCException("-30000", "上传文件必须是jpg或png图片格式");
	
	public static final Exception ASSOCIATION_OBJECT = new BCException("-30000", "存在关联对象，不可删除");
	
	public static final Exception DATALEX_REFUND_EXPIRED = new BCException("-40000", "请求已经过期，请重新提交请求！");
	
	public static final BCException PNR_FAILED = new BCException("-30000", "定位失败");
	
	public static final BCException EXCEL_CELL_ERROR = new BCException("-30000", "%s行，%s!");
	
    /** 异常结果码 */
    private ResultCode  resultCode       = ResultCode.UN_KNOWN_EXCEPTION;
    
	protected String code;
	
	public BCException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public BCException(BCException exception, Object... params) {
		super(String.format(exception.getMessage(), params));
		this.code = exception.getCode();
	}
	
    /**
     * 创建一个<code>ItradeException</code>
     *
     * @param code    异常代码<code>ItradeResultCode</code>
     */
    public BCException(ResultCode resultCode) {
        super(resultCode.getDescription());
        this.resultCode = resultCode;
    }

    /**
     * 创建一个<code>ItradeException</code>
     *
     * @param code    异常代码<code>ItradeResultCode</code>
     * @param msg     异常描述
     */
    public BCException(ResultCode resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
    }
    
	public String getCode() {
		return code;
	}
	
    /**
     * Getter method for property <tt>resultCode</tt>.
     *
     * @return property value of resultCode
     */
    public ResultCode getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property <tt>resultCode</tt>.
     *
     * @param resultCode value to be assigned to property resultCode
     */
    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
    
}

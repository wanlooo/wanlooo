package commons.test.jsontest;

import com.cn.xxx.commons.util.JsonUtils;

public class Test {

	public static void main(String[] args) {
		
		String json = "{\"Name\":\"hzm\",\"Age\":12}";
		User jsonToPojo = JsonUtils.jsonToPojo(json, User.class);
		System.out.println(jsonToPojo);
	}
}

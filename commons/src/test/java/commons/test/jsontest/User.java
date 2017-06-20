package commons.test.jsontest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	@JsonProperty(value="Name")
	private String name;
	@JsonProperty(value="Age")
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
}

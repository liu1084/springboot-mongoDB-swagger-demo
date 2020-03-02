package com.jim.swagger.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @project: springboot-swagger-demo
 * @packageName: com.jim.swagger.entity
 * @author: Administrator
 * @date: 2020/3/2 16:07
 * @description：TODO
 */
@Data
public class CreateUserReqDTO implements Serializable {
	private static final long serialVersionUID = 2397647162539617765L;
	@NotNull(message = "用户名不能为空")
	private String name;
	@NotNull(message = "密码不能为空")
	private String password;
	@NotNull(message = "身份证号码不能为空")
	private String passportNumber;
}

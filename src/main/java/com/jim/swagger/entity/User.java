package com.jim.swagger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @project: springboot-swagger-demo
 * @packageName: com.jim.swagger.entity
 * @author: Administrator
 * @date: 2020/2/28 10:34
 * @description：TODO
 */
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 921132525001049913L;
	private String id;
	private String name;
	@JsonIgnore
	private String password;
	private String passportNumber;
	@JsonIgnore
	private Boolean isDeleted;
}

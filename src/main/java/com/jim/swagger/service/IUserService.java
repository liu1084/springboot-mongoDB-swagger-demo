package com.jim.swagger.service;

import com.jim.swagger.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @project: springboot-swagger-demo
 * @packageName: com.jim.swagger.service
 * @author: Administrator
 * @date: 2020/2/28 10:32
 * @description：TODO
 */
public interface IUserService {
	Optional<User> findUserById(String userId);
	Optional<User> findUserByName(String username);
	Optional<List<User>> findUsers();
	Optional<User> createUser(User user);
	Optional<User> updateUser(User user);
	Optional<User> deleteUser(String userId);
}

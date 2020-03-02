package com.jim.swagger.controller;

import com.jim.swagger.entity.CreateUserReqDTO;
import com.jim.swagger.entity.User;
import com.jim.swagger.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static com.jim.swagger.config.Content.URL_PREFIX;
import static com.jim.swagger.config.Content.URL_VERSION;

/**
 * @project: springboot-swagger-demo
 * @packageName: com.jim.swagger.controller
 * @author: Administrator
 * @date: 2020/3/2 15:05
 * @description：TODO
 */

@RestController
@RequestMapping(value = URL_PREFIX + URL_VERSION + "/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping(value = "/{username}")
	public ResponseEntity<User> findUsreByUsername(@PathVariable("username") String username) {
		Optional<User> userOptional = userService.findUserByName(username);
		return ResponseEntity.of(userOptional);
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<User> findUserById(@PathVariable("userId") String userId) {
		Optional<User> userOptional = userService.findUserById(userId);
		return ResponseEntity.of(userOptional);
	}

	@GetMapping
	public ResponseEntity<List<User>> findUsers() {
		Optional<List<User>> usersOptional = userService.findUsers();
		return ResponseEntity.of(usersOptional);
	}

	@PutMapping
	public ResponseEntity<User> updateUser(@NotNull @Validated @RequestBody User user) {
		Optional<User> userOptional = userService.updateUser(user);
		return ResponseEntity.of(userOptional);
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@NotNull @Validated @RequestBody CreateUserReqDTO reqDTO) {
		User user = new User();
		BeanUtils.copyProperties(reqDTO,user);
		Optional<User> userOptional = userService.createUser(user);
		return ResponseEntity.of(userOptional);
	}

	@DeleteMapping
	public ResponseEntity<User> deleteUser(@NotNull @RequestParam("userId") String userId) {
		Optional<User> userOptional = userService.deleteUser(userId);
		return ResponseEntity.of(userOptional);
	}
}

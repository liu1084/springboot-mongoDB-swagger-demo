package com.jim.swagger.service.impl;

import com.jim.swagger.entity.User;
import com.jim.swagger.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @project: springboot-swagger-demo
 * @packageName: com.jim.swagger.service.impl
 * @author: Administrator
 * @date: 2020/2/28 10:35
 * @description：TODO
 */

@Service
public class UserServiceImpl implements IUserService {
	private MongoTemplate template;

	@Autowired
	public UserServiceImpl(MongoTemplate template) {
		this.template = template;
	}

	@Override
	public Optional<User> findUserById(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(userId));
		User user = template.findOne(query, User.class);
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> findUserByName(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(username));
		User user = template.findOne(query, User.class);
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<List<User>> findUsers() {
		Query query = new Query();
		query.addCriteria(Criteria.where("isDeleted")).equals(false);
		List<User> users = template.find(query, User.class);
		return Optional.of(users);
	}

	@Override
	public Optional<User> createUser(User user) {
		String password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(password);
		template.save(user);
		return Optional.of(user);
	}

	@Override
	public Optional<User> updateUser(User user) {
		String userId = user.getId();
		Optional<User> userOptional = findUserById(userId);
		userOptional.ifPresent(u -> {
			BeanUtils.copyProperties(user,u);
			template.save(u);
		});
		return Optional.of(user);
	}

	@Override
	public Optional<User> deleteUser(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(userId));
		User user = template.findOne(query,User.class);
		if (user != null) {
			Boolean isDeleted = true;
			user.setIsDeleted(isDeleted);
			template.save(user);
		}
		return Optional.ofNullable(user);
	}
}

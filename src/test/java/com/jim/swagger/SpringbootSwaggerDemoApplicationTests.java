package com.jim.swagger;

import com.jim.swagger.entity.User;
import com.jim.swagger.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

@SpringBootTest
class SpringbootSwaggerDemoApplicationTests {

	@Autowired
	private IUserService userService;

	@Autowired
	private MongoTemplate template;

	@BeforeEach
	public void initDB() {
		template.dropCollection("user");
	}

	@Test
	public void testFindUserByUsrname() {
		String name = "jim";
		User u = new User();
		u.setIsDeleted(false);
		u.setPassword("110");
		u.setName(name);
		u.setPassportNumber("SN001");

		userService.createUser(u);
		Optional<User> userOptional = userService.findUserByName(name);
		userOptional.ifPresent((user) -> {
			Assertions.assertNotEquals(null,user);
			user.setPassportNumber("SN002");
			Optional<User> updateOptional = userService.updateUser(user);
			updateOptional.ifPresent((update) -> {
				String passportNumber = update.getPassportNumber();
				Assertions.assertNotEquals(null,passportNumber);
				Assertions.assertEquals("SN002",passportNumber);
			});

			String userId = user.getId();
			Optional<User> deleteOptional = userService.deleteUser(userId);
			deleteOptional.ifPresent((delete) -> {
				Optional<User> queryUserByIdOptional = userService.findUserById(userId);
				Assertions.assertNotEquals(null,queryUserByIdOptional);
				User user1 = queryUserByIdOptional.get();
				Assertions.assertEquals(true,user1.getIsDeleted());
			});
		});
	}
}

package cc.mobireach.api.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cc.mobireach.api.data.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void findUserByIdTest() throws Exception {
		Assert.assertNotNull(userRepository.findByUserId(1));
	}
}

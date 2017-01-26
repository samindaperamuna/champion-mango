package cc.mobireach.api.data;

import org.springframework.data.repository.Repository;

import cc.mobireach.api.model.User;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Integer> {

	User findByUserId(Integer id);

	User findByUsername(String username);

	User save(User user);
}

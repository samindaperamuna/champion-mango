package cc.mobireach.api.data;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.mobireach.api.model.UserType;

@org.springframework.stereotype.Repository
public interface UserTypeRepository extends Repository<UserType, Integer> {

	List<UserType> findAll();

	UserType findByUserTypeId(Integer id);
}

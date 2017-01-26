package cc.mobireach.api.data;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.mobireach.api.model.Member;
import cc.mobireach.api.model.Subscription;
import cc.mobireach.api.model.User;

@org.springframework.stereotype.Repository
public interface SubscriptionRepository extends Repository<Subscription, Integer> {
	User findBySubscriptionId(Integer id);

	List<Subscription> findByMember(Member member);

	Subscription save(Subscription user);
}

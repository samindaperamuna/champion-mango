package cc.mobireach.api.data;

import org.springframework.data.repository.Repository;

import cc.mobireach.api.model.Member;

@org.springframework.stereotype.Repository
public interface MemberRespository extends Repository<Member, Integer> {

	Member findByMemberId(Integer memberId);

	Member save(Member member);
}

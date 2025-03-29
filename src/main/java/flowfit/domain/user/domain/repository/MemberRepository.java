package flowfit.domain.user.domain.repository;

import flowfit.domain.user.domain.entity.User;
import flowfit.domain.user.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}

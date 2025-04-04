package flowfit.domain.ptrelation.domain.repository;

import flowfit.domain.ptrelation.domain.entity.prepare.PreparePt;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreparePtRepository extends JpaRepository<PreparePt, Long> {

    Optional<PreparePt> findByTrainerAndMember(Trainer trainer, Member member);
}

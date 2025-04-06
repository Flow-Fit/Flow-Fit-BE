package flowfit.domain.ptrelation.domain.repository;

import flowfit.domain.ptrelation.domain.entity.prepare.PreparePt;
import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PtRelationRepository extends JpaRepository<PtRelation, Long> {

    Optional<PtRelation> findByTrainerAndMember(Trainer trainer, Member member);

    Optional<PtRelation> findByMember(Member member);

    List<PtRelation> findAllByMember(Member member);
}

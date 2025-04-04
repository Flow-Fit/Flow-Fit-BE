package flowfit.domain.user.domain.repository;

import flowfit.domain.user.domain.entity.ptrelation.PtRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PtRelationRepository extends JpaRepository<PtRelation, Long> {


}

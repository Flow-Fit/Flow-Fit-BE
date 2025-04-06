package flowfit.domain.ptsession.domain.repository;

import feign.Param;
import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import flowfit.domain.ptsession.domain.entity.PtSession;
import flowfit.domain.user.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PtSessionRepository extends JpaRepository<PtSession, Long> {
    @Query("SELECT p FROM PtSession p WHERE p.ptRelation = :ptRelation AND " +
            "YEAR(p.startTime) = :year AND MONTH(p.startTime) = :month AND DAY(p.startTime) = :day")
    List<PtSession> findByPtRelationAndDate(@Param("ptRelation") PtRelation ptRelation,
                                            @Param("year") int year,
                                            @Param("month") int month,
                                            @Param("day") int day);


    @Query("SELECT p FROM PtSession p WHERE p.ptRelation = :ptRelation AND " +
            "YEAR(p.startTime) = :year AND MONTH(p.startTime) = :month")
    List<PtSession> findByPtRelationAndDateAll(@Param("ptRelation") PtRelation ptRelation,
                                            @Param("year") int year,
                                            @Param("month") int month
   );


    List<PtSession> findAllByPtRelation(PtRelation ptRelation);
}

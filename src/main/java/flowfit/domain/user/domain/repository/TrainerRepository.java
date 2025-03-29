package flowfit.domain.user.domain.repository;

import flowfit.domain.user.domain.entity.User;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {


}

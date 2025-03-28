package flowfit.domain.user.domain.repository;

import flowfit.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // Username으로 User 찾기

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}

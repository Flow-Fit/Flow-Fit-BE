package flowfit.global.jwt.domain.repository;


import flowfit.global.jwt.domain.entity.GoogleJsonWebToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleJsonWebTokenRepository extends CrudRepository<GoogleJsonWebToken, String> {
}
package flowfit.flowfit.domain.user.application.service;

import flowfit.domain.user.domain.entity.User;

public interface UserService {
    User getUserById(String userId);
}
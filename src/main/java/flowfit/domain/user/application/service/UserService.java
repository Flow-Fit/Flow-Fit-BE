package flowfit.domain.user.application.service;


import flowfit.domain.user.presentation.dto.req.UserJoinDto;
import flowfit.domain.user.presentation.dto.req.UserLoginDto;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface UserService {

    void userJoin(UserJoinDto joinDto, HttpServletResponse response) throws IOException;
    void userLoin(UserLoginDto joinDto,HttpServletResponse response);

}
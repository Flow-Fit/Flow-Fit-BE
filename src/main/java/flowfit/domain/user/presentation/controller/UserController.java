package flowfit.domain.user.presentation.controller;

import flowfit.domain.user.application.service.UserService;
import flowfit.domain.user.domain.entity.User;
import flowfit.domain.user.presentation.dto.req.UserJoinDto;
import flowfit.domain.user.presentation.dto.req.UserLoginDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public void UserJoin(UserJoinDto joinDto, HttpServletResponse response) throws IOException {
        userService.userJoin(joinDto, response);
    }

    @PostMapping("/login")
    public void UserJoin(UserLoginDto loginDto){
        userService.userLoin(loginDto);
    }

}
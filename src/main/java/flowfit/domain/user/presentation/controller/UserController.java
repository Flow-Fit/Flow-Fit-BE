//package flowfit.domain.user.presentation.controller;
//
//import flowfit.domain.user.application.service.UserService;
//import flowfit.domain.user.domain.entity.User;
//import flowfit.domain.user.domain.repository.UserRepository;
//import flowfit.domain.user.presentation.dto.req.UserJoinDto;
//import flowfit.domain.user.presentation.dto.req.UserLoginDto;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/users")
//@RequiredArgsConstructor
//@Slf4j
//public class UserController {
//    private final UserService userService;
//
//    private final UserRepository userRepository;
//    @PostMapping("/join")
//    public void UserJoin(@RequestBody UserJoinDto joinDto, HttpServletResponse response) throws IOException {
//        userService.userJoin(joinDto, response);
//    }
//
//    @PostMapping("/login")
//    public void UserJoin(@RequestBody UserLoginDto loginDto, HttpServletResponse response) throws IOException {
//        userService.userLoin(loginDto,response);
//    }
//
//
//
//
//}
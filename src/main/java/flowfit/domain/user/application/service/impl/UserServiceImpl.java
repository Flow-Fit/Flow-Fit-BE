//package flowfit.domain.user.application.service.impl;
//
//
//import flowfit.domain.oauth2.application.service.CreateAccessTokenAndRefreshTokenService;
//
//import flowfit.domain.user.application.service.UserService;
//import flowfit.domain.user.domain.entity.Role;
//import flowfit.domain.user.domain.entity.User;
//import flowfit.domain.user.domain.entity.member.Member;
//import flowfit.domain.user.domain.entity.trainer.Trainer;
//import flowfit.domain.user.domain.repository.MemberRepository;
//import flowfit.domain.user.domain.repository.TrainerMemberRepository;
//import flowfit.domain.user.domain.repository.TrainerRepository;
//import flowfit.domain.user.domain.repository.UserRepository;
//import flowfit.domain.user.infra.exception.*;
//import flowfit.domain.user.presentation.dto.req.UserJoinDto;
//import flowfit.domain.user.presentation.dto.req.UserLoginDto;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Sinks;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.UUID;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//    private final CreateAccessTokenAndRefreshTokenService createAccessTokenAndRefreshTokenService;
//    private final UserRepository userRepository;
//    private final TrainerRepository trainerRepository;
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void userJoin(UserJoinDto joinDto, HttpServletResponse response) throws IOException {
//        User userN = userRepository.findByUsername(joinDto.username()).orElse(null);
//        User userE = userRepository.findByEmail(joinDto.email()).orElse(null);
//        User user;
//        if (userN != null) {
//            throw new UserNameExistException();
//        } else if (userE != null) {
//            throw new EmailExistException();
//        } else {
//            String userId = UUID.randomUUID().toString();
//            user = User.builder()
//                    .id(userId)
//                    .email(joinDto.email())
//                    .username(joinDto.username())
//                    .password(passwordEncoder.encode(joinDto.password()))
//                    .name(joinDto.name())
//                    .profile(joinDto.profile())
//                    .phoneNumber(joinDto.phoneNumber())
//                    .role(joinDto.role())
//                    .build();
//
//            userRepository.save(user);
//            if (user.getRole().equals(Role.MEMBER)) {
//                Member member = Member.builder().user(user).build();
//                memberRepository.save(member);
//            } else if (user.getRole().equals(Role.TRAINER)) {
//                Trainer trainer = Trainer.builder().user(user).build();
//                trainerRepository.save(trainer);
//            }
//        }
//
//
//        createToken(response, user);
//    }
//
//    @Override
//    public void userLoin(UserLoginDto loginDto, HttpServletResponse response) throws IOException {
//        User existUser = userRepository.findByUsername(loginDto.username()).orElse(null);
//
//        if (existUser == null) {
//
//            throw new UserNameNotException();
//
//        }
//
//        if (!BCrypt.checkpw(loginDto.password(), existUser.getPassword())) {
//            throw new PasswordNotCorrectException();
//        }
//
//        createToken(response, existUser);
//    }
//
//    public void createToken(HttpServletResponse response, User user) throws IOException {
//
//        Map<String, String> tokens = createAccessTokenAndRefreshTokenService.createAccessTokenAndRefreshToken(user.getId(), user.getRole(), user.getEmail());
//
//        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tokens.get("access_token"));
//        response.addHeader(HttpHeaders.SET_COOKIE, tokens.get("refresh_token_cookie"));
//
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.setContentType("application/json");
//
//        response.getWriter().write("Successfully Login");
//    }
//
//}

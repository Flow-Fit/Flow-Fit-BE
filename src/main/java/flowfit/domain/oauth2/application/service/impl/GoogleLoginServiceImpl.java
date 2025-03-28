//package flowfit.domain.oauth2.application.service.impl;
//
//
//import flowfit.domain.oauth2.application.service.*;
//import flowfit.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
//import flowfit.domain.oauth2.presentation.dto.response.OAuth2UserResponse;
//import flowfit.domain.user.domain.entity.Role;
//
//
//import flowfit.global.jwt.domain.entity.GoogleJsonWebToken;
//import flowfit.global.jwt.domain.repository.GoogleJsonWebTokenRepository;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//@Slf4j
//public class GoogleLoginServiceImpl implements GoogleLoginService {
//
//    private final  GoogleLoginService googleAccessTokenAndRefreshTokenService;
//    private final GoogleUserService googleUserService;
//    private final CreateAccessTokenAndRefreshTokenService createAccessTokenAndRefreshTokenService;
//    private final GoogleUserCreateService googleUserCreateService;
//    private final GoogleJsonWebTokenRepository googleTokenRepository;
//
//    @Override
//    public void login(String code, HttpServletResponse response) throws IOException {
//        OAuth2TokenResponse oAuth2TokenResponse = googleAccessTokenAndRefreshTokenService.getAccessTokenAndRefreshToken(code);
//
//        OAuth2UserResponse oAuth2UserResponse = googleUserService.getUser(oAuth2TokenResponse.accessToken());
//
//        Map<String, String> values = googleUserCreateService.createGoogleUser(oAuth2TokenResponse,  oAuth2UserResponse);
//
//        String userId = values.get("id");
//        Role role = Role.valueOf(values.get("role"));
//        String userEmail = values.get("email");
//
//        // Google 토큰 저장
//        GoogleJsonWebToken googleToken = GoogleJsonWebToken.builder()
//                .userId(userId)
//                .accessToken(oAuth2TokenResponse.accessToken())
//                .refreshToken(oAuth2TokenResponse.refreshToken())
//                .build();
//        googleTokenRepository.save(googleToken);
//
//        Map<String, String> tokens = createAccessTokenAndRefreshTokenService.createAccessTokenAndRefreshToken(userId, role, userEmail);
//
//        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tokens.get("access_token"));
//        response.addHeader(HttpHeaders.SET_COOKIE, tokens.get("refresh_token_cookie"));
//
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.setContentType("application/json");
//
//        response.getWriter().write("Successfully Login");
//    }
//}

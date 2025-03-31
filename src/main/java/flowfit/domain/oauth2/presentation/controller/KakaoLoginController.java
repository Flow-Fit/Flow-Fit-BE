package flowfit.domain.oauth2.presentation.controller;

import flowfit.domain.oauth2.application.service.KakaoLoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
@Slf4j
public class KakaoLoginController {

    private final KakaoLoginService KakaoLoginService;

    @GetMapping("/callback")
    public void login(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        KakaoLoginService.login(code, response);
        log.info("응답 Authorization 헤더: {}", response.getHeader(HttpHeaders.AUTHORIZATION));
        log.info("응답 Set-Cookie 헤더: {}", response.getHeader(HttpHeaders.SET_COOKIE));
    }
}

package flowfit.domain.oauth2.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import flowfit.domain.oauth2.application.service.KakaoLoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
@Slf4j
public class KakaoLoginController {

    private final KakaoLoginService KakaoLoginService;

    @GetMapping("/callback")
    public void login(@RequestParam("code") String code,  @RequestParam(required = false) String state,
                      HttpServletResponse response) throws IOException {
        Map<String, String> tokens = KakaoLoginService.login(code,response,state);
        log.info("✅ Response Headers - Authorization: {}", response.getHeader(HttpHeaders.AUTHORIZATION));
        log.info("✅ Response Headers - Set-Cookie: {}", response.getHeader(HttpHeaders.SET_COOKIE));

    }

}

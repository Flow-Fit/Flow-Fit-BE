package flowfit.domain.oauth2.presentation.controller;

import flowfit.domain.oauth2.application.service.LoginLinkService;
import flowfit.domain.oauth2.presentation.dto.request.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
public class LoginLinkController {

    private final LoginLinkService loginLinkService;

    @GetMapping("/login")
    public ResponseEntity<String> loginPage(@RequestParam("type") String type) {
        return ResponseEntity.status(200).body(loginLinkService.getLoginLink(type));
    }
}

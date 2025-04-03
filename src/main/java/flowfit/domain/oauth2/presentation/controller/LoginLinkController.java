package flowfit.domain.oauth2.presentation.controller;

import flowfit.domain.oauth2.application.service.LoginLinkService;
import flowfit.domain.oauth2.presentation.dto.request.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
public class LoginLinkController {

    private final LoginLinkService loginLinkService;

    @GetMapping("/login")
    public ResponseEntity<String> loginPage(@RequestBody LoginRequestDTO requestDTO) {
        return ResponseEntity.status(200).body(loginLinkService.getLoginLink(requestDTO.type()));
    }
}

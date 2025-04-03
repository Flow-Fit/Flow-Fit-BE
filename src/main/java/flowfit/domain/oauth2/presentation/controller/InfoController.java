package flowfit.domain.oauth2.presentation.controller;

import flowfit.domain.oauth2.application.service.InfoService;
import flowfit.domain.oauth2.presentation.dto.request.InfoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
@Slf4j
public class InfoController {

    private final InfoService infoService;
    @PostMapping("/info")
    public void info(@RequestBody  InfoRequestDTO info, @AuthenticationPrincipal String userId) throws NoSuchAlgorithmException {
        infoService.addInfo(info, userId);
    }
}

package flowfit.domain.ptrelation.presentation.controller;

import flowfit.domain.ptrelation.application.service.TrainerCodeService;
import flowfit.domain.ptrelation.presentation.dto.req.TrainerCodeDeleteRequestDto;
import flowfit.domain.ptrelation.presentation.dto.req.TrainerCodeRequestDto;
import flowfit.domain.ptrelation.presentation.dto.res.TrainerCodeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/trainer/code")
@RequiredArgsConstructor
public class TrainerCodeController {

    private final TrainerCodeService trainerCodeService;

    @GetMapping
    public ResponseEntity<TrainerCodeResponse> trainerCodeGet(@RequestParam String code) {
        return ResponseEntity.status(200).body(TrainerCodeResponse.of(trainerCodeService.trainerCodeGet(code)));
    }

    @PostMapping
    public void trainerCodePost(@RequestBody TrainerCodeRequestDto dto, @AuthenticationPrincipal String userId) {
        trainerCodeService.trainerCodePost(dto, userId);
    }



    @DeleteMapping
    public void trainerCodeDelete(@RequestBody TrainerCodeDeleteRequestDto dto) {
        trainerCodeService.trainerCodeDelete(dto);
    }
}

package flowfit.domain.ptrelation.application.service;

import flowfit.domain.ptrelation.presentation.dto.req.TrainerCodeDeleteRequestDto;
import flowfit.domain.ptrelation.presentation.dto.req.TrainerCodeRequestDto;

public interface TrainerCodeService {

    String trainerCodeGet(TrainerCodeRequestDto dto);

    void trainerCodePost(TrainerCodeRequestDto dto, String userId);

    void trainerCodeDelete(TrainerCodeDeleteRequestDto dto);
}

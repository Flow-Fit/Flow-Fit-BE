package flowfit.domain.ptrelation.application.service;

import flowfit.domain.ptrelation.presentation.dto.req.PtRelationRequestDto;
import flowfit.domain.ptrelation.presentation.dto.res.MemberRelationResponse;
import flowfit.domain.ptrelation.presentation.dto.res.TrainerRelationDetailResponse;
import flowfit.domain.ptrelation.presentation.dto.res.TrainerRelationResponse;
import java.util.List;

public interface PtRelationService {

    void ptRelationSave(PtRelationRequestDto dto, String userId);

    List<MemberRelationResponse> userRelation(String userId);

    List<TrainerRelationResponse> trainerRelation(String userId);

    TrainerRelationDetailResponse trainerRelationDetail(String userId, Long relationId);
}

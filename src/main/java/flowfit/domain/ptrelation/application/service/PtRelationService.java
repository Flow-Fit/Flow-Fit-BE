package flowfit.domain.ptrelation.application.service;

import flowfit.domain.ptrelation.presentation.dto.req.PtRelationRequestDto;

public interface PtRelationService {

    void ptRelationSave(PtRelationRequestDto dto, String userId);
}

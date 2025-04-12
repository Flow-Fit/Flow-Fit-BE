package flowfit.domain.ptsession.presentation.controller;

import flowfit.domain.ptrelation.application.service.PtRelationService;
import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import flowfit.domain.ptrelation.domain.repository.PtRelationRepository;
import flowfit.domain.ptsession.presentation.dto.res.ClientSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientSearchController {

    private final PtRelationRepository ptRelationRepository;

    @GetMapping("/search")
    public ResponseEntity<?> searchClientByName(@RequestParam String name) {
        String trainerId = SecurityContextHolder.getContext().getAuthentication().getName();

        List<PtRelation> relations = ptRelationRepository
                .findAllByTrainer_IdAndMember_NameContaining(trainerId, name);

        List<ClientSearchResponse> clients = relations.stream()
                .map(ClientSearchResponse::from)
                .toList();

        return ResponseEntity.ok().body(new ClientSearchResponse.Wrapper(clients));
    }
}

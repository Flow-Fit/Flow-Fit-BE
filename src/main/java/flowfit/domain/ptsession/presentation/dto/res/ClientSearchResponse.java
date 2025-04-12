package flowfit.domain.ptsession.presentation.dto.res;

import flowfit.domain.ptrelation.domain.entity.ptrelation.PtRelation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ClientSearchResponse {

    private Long memberId;
    private String name;
    private String phone;
    private int remainingSessions;

    public static ClientSearchResponse from(PtRelation relation) {
        return ClientSearchResponse.builder()
                .memberId(Long.parseLong(relation.getMember().getId()))
                .name(relation.getMember().getName())
                .phone(relation.getMember().getPhoneNumber())
                .remainingSessions(relation.getSession())
                .build();
    }

    @Getter
    @AllArgsConstructor
    public static class Wrapper {
        private List<ClientSearchResponse> clients;
    }
}

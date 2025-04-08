
package flowfit.domain.ptsession.presentation.dto.res;

public record UserIssueResponse(

        String conflict

) {    public static UserIssueResponse of(String conflict) {
    return new UserIssueResponse(conflict);
}
}

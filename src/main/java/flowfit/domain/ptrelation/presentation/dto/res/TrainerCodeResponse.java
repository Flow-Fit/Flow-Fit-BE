package flowfit.domain.ptrelation.presentation.dto.res;


public record TrainerCodeResponse(
        String trainerName
) {
    public static TrainerCodeResponse of(String trainerName) {
        return new TrainerCodeResponse(trainerName);
    }
}

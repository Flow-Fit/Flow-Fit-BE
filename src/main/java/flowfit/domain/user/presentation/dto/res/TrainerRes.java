package flowfit.domain.user.presentation.dto.res;

public record TrainerRes(
        String name,

        String code
) {
    public static TrainerRes of(String name, String code) {
        return new TrainerRes(name, code);
    }
}

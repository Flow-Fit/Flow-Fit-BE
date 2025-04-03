package flowfit.domain.oauth2.application.service.impl;

import flowfit.domain.oauth2.application.service.InfoService;
import flowfit.domain.oauth2.presentation.dto.request.InfoRequestDTO;
import flowfit.domain.user.domain.entity.Role;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import flowfit.domain.user.domain.repository.MemberRepository;
import flowfit.domain.user.domain.repository.TrainerRepository;
import flowfit.domain.user.infra.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {

    private final MemberRepository memberRepository;

    private final TrainerRepository trainerRepository;

    @Override
    public void addInfo(InfoRequestDTO info, String userId) throws NoSuchAlgorithmException {
        if (info.type().equals(Role.TRAINER.name())) {
            Trainer trainer = trainerRepository.findById(userId).orElse(null);
            if (trainer != null) {
                trainer.updateStatus(true);
                trainer.updateGymPlace(info.gymPlace());
                trainer.updateTrainerCode(generateHashFromString(info.gymPlace()));
            } else {
                throw new UserNotFoundException("트레이너 정보가 없습니다.");
            }
        } else {

            Member member = memberRepository.findById(userId).orElse(null);
            if (member != null) {
                member.updateStatus(true);
                member.updateMemberInfo(info.phoneNumber(), info.height(), info.weight(), info.birthDate());
            } else {
                throw new UserNotFoundException("회원 정보가 없습니다.");
            }
        }
    }

    public String generateHashFromString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(input.getBytes());

        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < 5; i++) {  // 원하는 길이만큼만 사용 (예: 5글자)
            hexString.append(Integer.toHexString(0xFF & hash[i]));
        }

        return hexString.toString().toUpperCase();
    }
}

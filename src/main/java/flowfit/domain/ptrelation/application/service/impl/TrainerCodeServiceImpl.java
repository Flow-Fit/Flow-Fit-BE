package flowfit.domain.ptrelation.application.service.impl;

import flowfit.domain.ptrelation.application.service.TrainerCodeService;
import flowfit.domain.ptrelation.infra.exception.PrepareTrainerExistException;
import flowfit.domain.user.domain.entity.member.Member;
import flowfit.domain.user.domain.entity.trainer.Trainer;
import flowfit.domain.ptrelation.domain.entity.prepare.PreparePt;
import flowfit.domain.user.domain.repository.MemberRepository;
import flowfit.domain.ptrelation.domain.repository.PreparePtRepository;
import flowfit.domain.user.domain.repository.TrainerRepository;
import flowfit.domain.ptrelation.infra.exception.TrainerCodeException;
import flowfit.domain.user.infra.exception.UserNotFoundException;
import flowfit.domain.ptrelation.presentation.dto.req.TrainerCodeDeleteRequestDto;
import flowfit.domain.ptrelation.presentation.dto.req.TrainerCodeRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TrainerCodeServiceImpl implements TrainerCodeService {

    private final TrainerRepository trainerRepository;

    private final MemberRepository memberRepository;

    private final PreparePtRepository preparePtRepository;

    @Override
    public String trainerCodeGet(String code) {
        Trainer trainer = trainerRepository.findByTrainerCode(code).orElse(null);

        if (trainer == null) {
            throw new TrainerCodeException();
        }
        return trainer.getName();
    }

    @Override
    public void trainerCodePost(TrainerCodeRequestDto dto, String userId) {
        Trainer trainer = trainerRepository.findByTrainerCode(dto.code()).orElse(null);

        if (trainer == null) {
            throw new TrainerCodeException();
        }
        Member member = memberRepository.findById(userId).orElse(null);

        if (member == null) {
            throw new UserNotFoundException("일치하는 회원 정보가 없습니다.");
        }

        PreparePt findPreparePt = preparePtRepository.findByTrainerAndMember(trainer, member).orElse(null);

        if (findPreparePt == null) {
            PreparePt preparePt = PreparePt.
                    builder().
                    member(member).
                    trainer(trainer).
                    build();

            preparePtRepository.save(preparePt);
        } else {
            throw new PrepareTrainerExistException();
        }
    }

    @Override
    public void trainerCodeDelete(TrainerCodeDeleteRequestDto dto) {
        PreparePt preparePt = preparePtRepository.findById(dto.id()).orElse(null);
        if (preparePt == null) {
            throw new TrainerCodeException();
        }
        preparePtRepository.delete(preparePt);
    }
}

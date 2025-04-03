package flowfit.domain.oauth2.application.service;

import flowfit.domain.oauth2.presentation.dto.request.InfoRequestDTO;

import java.security.NoSuchAlgorithmException;

public interface InfoService {

    void addInfo(InfoRequestDTO info, String userId) throws NoSuchAlgorithmException;
}

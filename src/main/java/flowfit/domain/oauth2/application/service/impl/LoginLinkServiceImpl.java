package flowfit.domain.oauth2.application.service.impl;

import flowfit.domain.oauth2.application.service.LoginLinkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//@Service
public class LoginLinkServiceImpl implements LoginLinkService {

    @Value("${oauth2.base-url}")
    private String baseUrl;

    @Value("${oauth2.client-id}")
    private String clientId;

    @Value("${oauth2.redirect-uri}")
    private String redirectUri;

    @Override
    public String getLoginLink() {
        return baseUrl +
                "?client_id=" +
                clientId +
                "&redirect_uri=" +
                redirectUri +
                "&response_type=code&" +
                "access_type=offline&" +
                "scope=https://www.googleapis.com/auth/userinfo.email+" +
                "https://www.googleapis.com/auth/userinfo.profile+" +
                "https://www.googleapis.com/auth/calendar+" +
                "https://www.googleapis.com/auth/calendar.events" +
                "&prompt=consent";
    }

}
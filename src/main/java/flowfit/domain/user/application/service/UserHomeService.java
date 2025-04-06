package flowfit.domain.user.application.service;


import flowfit.domain.user.presentation.dto.req.UserAlarmDto;
import flowfit.domain.user.presentation.dto.req.UserJoinDto;
import flowfit.domain.user.presentation.dto.req.UserLoginDto;
import flowfit.domain.user.presentation.dto.res.MemberCalendarResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface UserHomeService {

    void userJoin(UserJoinDto joinDto, HttpServletResponse response) throws IOException;
    void userLoin(UserLoginDto joinDto,HttpServletResponse response) throws IOException;

    void userAlarm(UserAlarmDto dto, String userId);

    List<MemberCalendarResponse> userCalendarDate(int year, int month, int date, String userId);

    List<MemberCalendarResponse> userCalendarMonth(int year, int month, String userId);

}
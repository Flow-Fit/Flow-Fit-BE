package flowfit.domain.user.presentation.controller;

import flowfit.domain.user.application.service.UserHomeService;
import flowfit.domain.user.presentation.dto.req.UserAlarmDto;
import flowfit.domain.user.presentation.dto.req.UserJoinDto;
import flowfit.domain.user.presentation.dto.req.UserLoginDto;
import flowfit.domain.user.presentation.dto.res.MemberCalendarResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserHomeController {
    private final UserHomeService userHomeService;


    //@PostMapping("/join")
    public void UserJoin(@RequestBody UserJoinDto joinDto, HttpServletResponse response) throws IOException {
    //    userService.userJoin(joinDto, response);
    }

    //@PostMapping("/login")
    public void UserJoin(@RequestBody UserLoginDto loginDto, HttpServletResponse response) throws IOException {
      //  userService.userLoin(loginDto, response);
    }

    // 알람 활성화
    @PatchMapping("/alarm")
    public void UserAlarm(@RequestBody UserAlarmDto dto, @AuthenticationPrincipal String userId) {

        userHomeService.userAlarm(dto, userId);
    }

    // 날짜별 조회
    @GetMapping("/calendar")
    public ResponseEntity<List<MemberCalendarResponse>> serCalendarDate(@RequestParam int year,
                                                                        @RequestParam int month,
                                                                        @RequestParam int date, @AuthenticationPrincipal String userId) {
        List<MemberCalendarResponse> res = userHomeService.userCalendarDate(year, month, date, userId);
        return ResponseEntity.status(200).body(res);
    }

    // 월별 조회
    @GetMapping("/calendar/all")
    public ResponseEntity<List<MemberCalendarResponse>> userSessionAll(@RequestParam int year,
                                                                       @RequestParam int month, @AuthenticationPrincipal String userId) {
        List<MemberCalendarResponse> res = userHomeService.userCalendarMonth(year, month, userId);

        return ResponseEntity.status(200).body(res);
    }

}
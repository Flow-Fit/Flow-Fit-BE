package flowfit.flowfit.domain.user.presentation.controller;

import flowfit.domain.user.application.service.UserService;
import flowfit.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public Map<String, String> getUserInfo(@PathVariable String userId) {
        User user = userService.getUserById(userId);

        Map<String, String> response = new HashMap<>();
        response.put("name", user.getName());
        response.put("mail", user.getMail());

        return response;
    }
}
package com.epam.gym.controller;

import com.epam.gym.entity.dto.request.ChangeLoginDTO;
import com.epam.gym.entity.dto.request.LoginDTO;
import com.epam.gym.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    ResponseEntity<Void> login(@Valid @RequestBody LoginDTO loginDTO) {
        userService.login(loginDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-login")
    ResponseEntity<Void> changeLogin(@Valid @RequestBody ChangeLoginDTO changeLoginDTO) {
        userService.changeLogin(changeLoginDTO);
        return ResponseEntity.ok().build();
    }
}

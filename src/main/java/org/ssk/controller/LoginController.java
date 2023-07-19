package org.ssk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.ssk.service.LoginService;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public String loginPage(){
        return "/login.html";
    }
    @PostMapping
    public String login(String nickname){
        log.info("nickname : "+ nickname);
        loginService.login(nickname);
        return "redirect:/chat";
    }
}

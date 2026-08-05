package com.desarrollo.algarrobo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.desarrollo.algarrobo.security.CaptchaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final CaptchaService captchaService;

    public LoginController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        captchaService.generarCaptcha(session);
        model.addAttribute("captchaPregunta", session.getAttribute("captchaPregunta"));
        return "login";
    }
}
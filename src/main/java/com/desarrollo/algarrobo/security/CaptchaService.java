package com.desarrollo.algarrobo.security;

import java.util.Random;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Component
public class CaptchaService {

    private final Random random = new Random();

    public void generarCaptcha(HttpSession session) {
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        session.setAttribute("captchaPregunta", a + " + " + b + " = ?");
        session.setAttribute("captchaRespuesta", a + b);
    }

    public boolean validar(HttpSession session, String respuestaUsuario) {
        Object correcto = session.getAttribute("captchaRespuesta");
        if (correcto == null || respuestaUsuario == null || respuestaUsuario.isBlank()) {
            return false;
        }
        try {
            return Integer.parseInt(respuestaUsuario.trim()) == (int) correcto;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
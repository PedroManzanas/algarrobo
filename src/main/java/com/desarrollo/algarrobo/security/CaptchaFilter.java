package com.desarrollo.algarrobo.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    private final CaptchaService captchaService;

    public CaptchaFilter(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean esLoginPost = "/login".equals(request.getServletPath()) && "POST".equalsIgnoreCase(request.getMethod());

        if (esLoginPost) {
            String respuestaUsuario = request.getParameter("captcha");
            HttpSession session = request.getSession(false);
            boolean captchaValido = session != null && captchaService.validar(session, respuestaUsuario);

            if (!captchaValido) {
                if (session != null) {
                    captchaService.generarCaptcha(session);
                }
                response.sendRedirect(request.getContextPath() + "/login?captchaError");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
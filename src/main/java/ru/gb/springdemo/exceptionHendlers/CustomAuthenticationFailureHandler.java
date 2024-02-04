package ru.gb.springdemo.exceptionHendlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);

        String errorMessage = "Неправильные имя и пароль.";


        if (exception.getMessage().contains("User is disabled")) {
            errorMessage = "Пользователь не активирован.";
        } else if (exception.getMessage().contains("User account has expired")) {
            errorMessage = "Аккаунт пользователя просрочен.";
        } else if (exception.getMessage().contains("User not found")) {
            errorMessage = "Аккаунт пользователя не найден.";
        } else if (exception.getMessage().contains("Token not found")) {
            errorMessage = "Токен не найден.";
        } else if (exception.getMessage().contains("Token expired")) {
            errorMessage = "Токен просрочен.";
        } else if (exception.getMessage().contains("JWT expired")) {
            errorMessage = "JWT токен просрочен.";
        } else if (exception.getMessage().contains("Email exists")) {
            errorMessage = "Email уже существует.";
        }

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }
}

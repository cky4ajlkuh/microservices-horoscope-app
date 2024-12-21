package ru.ssau.authentication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ssau.authentication.dto.SignInRequest;
import ru.ssau.authentication.dto.SignUpRequest;
import ru.ssau.authentication.util.Role;
import ru.ssau.authentication.entity.User;
import ru.ssau.authentication.dto.AuthenticationResponse;
import ru.ssau.authentication.util.CalculateSign;

/**
 * Сервис по работе с регистрацией и аутентификацией пользователей
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public AuthenticationResponse signUp(SignUpRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();
        try {
            User user = User.builder()
                    .username(request.getUsername())
                    .birthday(request.getBirthday())
                    .zodiac(CalculateSign.getZodiacSign(request.getBirthday()))
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            if (request.getUsername().equals("admin")) {
                user.setRole(Role.ROLE_ADMIN);
            } else user.setRole(Role.ROLE_USER);
            userService.create(user);
            response.setToken(jwtService.generateToken(user));
            return response;
        } catch (Exception exception) {
            response.setError(exception.getMessage());
            return response;
        }
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public AuthenticationResponse signIn(SignInRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
            User user = userService.getByUsername(request.getUsername());
            response.setToken(jwtService.generateToken(user));
            return response;
        } catch (Exception exception) {
            response.setError("Неверный логин или пароль!");
            return response;
        }
    }
}

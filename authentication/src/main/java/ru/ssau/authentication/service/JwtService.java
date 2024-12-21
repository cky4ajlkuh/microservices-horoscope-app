package ru.ssau.authentication.service;

import org.springframework.stereotype.Service;
import ru.ssau.authentication.dto.Token;
import ru.ssau.authentication.entity.User;

/**
 * Сервис для создания токена
 */
@Service
public class JwtService {

    /**
     * Генерация токена
     *
     * @param user данные пользователя
     * @return токен
     */
    public Token generateToken(User user) {
        return new Token(user.getUsername(), user.getBirthday(), user.getZodiac());
    }
}


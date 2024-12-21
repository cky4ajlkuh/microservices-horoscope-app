package ru.ssau.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import ru.ssau.authentication.dto.AuthenticationResponse;
import ru.ssau.authentication.dto.SignInRequest;
import ru.ssau.authentication.dto.SignUpRequest;
import ru.ssau.authentication.exception.RequestException;
import ru.ssau.authentication.service.AuthenticationService;

@RestController
@ApplicationScope
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/registration")
    public AuthenticationResponse signUp(@RequestBody SignUpRequest user) {
        if (user == null) {
            throw new RequestException("На сервер пришел пустой запрос!");
        }
        return service.signUp(user);
    }

    @PostMapping(value = "/login")
    public AuthenticationResponse signIn(@RequestBody SignInRequest user) {
        if (user == null) {
            throw new RequestException("На сервер пришел пустой запрос!");
        }
        return service.signIn(user);
    }
}

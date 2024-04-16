package com.huna.basic.controller;

import org.springframework.web.bind.annotation.RestController;

import com.huna.basic.service.BasicService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final BasicService basicService;
    
    @GetMapping("/{principle}")
    public String getJwt(
        @PathVariable("principle") String principle
    ) {
        return basicService.getJwt(principle);
    }

    @PostMapping("/validation")
    public String jwtValidate(
        @RequestBody String jwt
    ) {
        return basicService.jwtValidate(jwt);
    }

    @GetMapping("/authentication/principle")
    public String authenticationPriciple (
        @AuthenticationPrincipal String username
    ) {
        return "접근 주체 : " + username;
    }
    


}

package com.huna.basic.controller;

import org.springframework.web.bind.annotation.RestController;

import com.huna.basic.service.BasicService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
}

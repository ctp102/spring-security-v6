package com.example.springsecurity6.controller;

import com.example.springsecurity6.dto.MemberRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Operation(summary = "Hello API", description = "Swagger 테스트용 API입니다.")
    @GetMapping("/")
    public String hello() {
        return "Hello, Swagger!";
    }

    @Operation(summary = "Hello API", description = "Swagger 테스트용 API입니다.")
    @GetMapping("/login")
    public String login(MemberRequestDto memberRequestDto) {
        return "Hello, Swagger!";
    }

}

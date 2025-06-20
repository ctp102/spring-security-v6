package com.example.springsecurity6.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 생성 요청 DTO")
public record CreateUserRequest(

        @Schema(description = "사용자 이름", example = "홍길동")
        String name,

        @Schema(description = "사용자 이메일", example = "hong@example.com")
        String email
) {}
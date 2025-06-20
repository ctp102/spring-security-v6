package com.example.springsecurity6.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 응답 DTO")
public record UserResponse(

        @Schema(description = "사용자 ID", example = "1")
        Long id,

        @Schema(description = "사용자 이름", example = "홍길동")
        String name,

        @Schema(description = "사용자 이메일", example = "hong@example.com")
        String email
) {}
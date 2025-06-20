package com.example.springsecurity6.controller;


import com.example.springsecurity6.dto.CreateUserRequest;
import com.example.springsecurity6.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "사용자 관리", description = "사용자 등록, 조회, 수정, 삭제 기능을 제공합니다.")
public class UserController {

    @PostMapping
    @Operation(
            summary = "사용자 등록",
            description = "신규 사용자를 시스템에 등록합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "등록 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청")
            }
    )
    public UserResponse createUser(
            @RequestBody CreateUserRequest request
    ) {
        return new UserResponse(1L, request.name(), request.email());
    }

    @GetMapping
    @Operation(summary = "모든 사용자 조회", description = "시스템에 등록된 모든 사용자를 조회합니다.")
    public List<UserResponse> getAllUsers() {
        return List.of(null);
    }

    @GetMapping("/{id}")
    @Operation(summary = "사용자 단건 조회", description = "ID에 해당하는 사용자 정보를 조회합니다.")
    public UserResponse getUserById(
            @Parameter(description = "사용자 ID", example = "1")
            @PathVariable Long id
    ) {
        return new UserResponse(id, "홍길동", "hong@example.com");
    }
}
package com.one.onegate.controller;

import com.one.onegate.dto.req.AccountCreationReqDto;
import com.one.onegate.exception.AppException;
import com.one.onegate.model.Account;
import com.one.onegate.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/v1/account/register")
    public ResponseEntity<?> register(
            @RequestBody AccountCreationReqDto req,
            @RequestHeader("User-Lang") String userLang
    ) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/account/register")
//                .toUriString());
        try {
            req.setUserLang(userLang);
            Account user = accountService.saveUser(req);
            return ResponseEntity.ok(user);
        } catch (AppException e) {
            return new ResponseEntity<>(e.getErrorResponse(new Object(), userLang), e.getHttpStatus());
        } catch (Exception e) {

            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());

            return ResponseEntity.badRequest().body(error);
        }
    }

//    @PostMapping("/v1/role/create")
//    public ResponseEntity<?> createUserRole() {
//
//    }
}

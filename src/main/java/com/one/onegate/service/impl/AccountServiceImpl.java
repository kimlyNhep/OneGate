package com.one.onegate.service.impl;

import com.one.onegate.dto.req.AccountCreationReqDto;
import com.one.onegate.exception.AppException;
import com.one.onegate.mapper.AccountMapper;
import com.one.onegate.mapper.RoleMapper;
import com.one.onegate.model.Account;
import com.one.onegate.service.AccountService;
import com.one.onegate.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account saveUser(AccountCreationReqDto req) throws AppException {
        Account user = new Account();
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        String email = req.getEmail();

        user.setEmail(email);
        if (email == null) {
            throw new AppException(ErrorCode.EMAIL_INVALID_FORMAT, req.getUserLang());
        }

        if (!pattern.matcher(email).matches()) {
            throw new AppException(ErrorCode.EMAIL_INVALID_FORMAT, req.getUserLang());
        }

        Integer countEmail = accountMapper.countByEmail(email);

        if (countEmail > 0) {
            throw new AppException(ErrorCode.EMAIL_EXIST, req.getUserLang());
        }

        Integer countUsername = accountMapper.countByUsername(user.getUsername());

        if (countUsername > 0) {
            throw new AppException(ErrorCode.ACCOUNT_EXIST, req.getUserLang());
        }

        user.setUsername(req.getUsername());
        String hashedPassword = passwordEncoder.encode(req.getPassword());
        user.setPassword(hashedPassword);

        try {
            return accountMapper.save(user);
        } catch (Exception e) {
            log.error("Error while creating account : {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ACCOUNT_FAIL, req.getUserLang());
        }
    }
}

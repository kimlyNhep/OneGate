package com.one.onegate.service;

import com.one.onegate.dto.req.AccountCreationReqDto;
import com.one.onegate.exception.AppException;
import com.one.onegate.model.Account;

public interface AccountService {
    Account saveUser(AccountCreationReqDto req) throws AppException;
}

package com.one.onegate.mapper;

import com.one.onegate.model.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    Account findByUsername(String username);
    Integer countByUsername(String username);
    Integer countByEmail(String email);
    Account save(Account account);
    List<Account> findAll();
//    Long count();
}

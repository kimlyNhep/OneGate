package com.one.onegate.dto.req;

import lombok.Data;

@Data
public class AccountCreationReqDto extends BaseReqDto {
    private String username;
    private String email;
    private String password;
    private Long roleId;
}

package com.one.onegate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long id;

    private String username;

    private String email;

    private String password;

    private String description;

    private String status;

    private Collection<Role> roles;

    private Date createdAt;

    private String createdBy;
}

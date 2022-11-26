package com.one.onegate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCode {
    private Long id;

    private String code;

    private String description;

    private String status;

    private String httpStatus;

    private String valueKH;

    private String valueEN;

    private String createdBy;

    private Date createdAt;
}

package com.one.onegate.mapper;

import com.one.onegate.model.ResponseCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResponseCodeMapper {
    List<ResponseCode> findAllByStatus(String status);
    ResponseCode save(ResponseCode responseCode);
}

package com.one.onegate.utils;

import com.one.onegate.startup.RedisCache;
import lombok.Data;

@Data
public class MessageResponse {
    public String status;
    public String message;
    public String data;

    public MessageResponse setSuccess(String message, String userLang) {
        this.setStatus(Constants.SUCCESS_CODE);
        this.setMessage(message);
        return this;
    }

    public MessageResponse setSuccess(String userLang) {
        this.setStatus(Constants.SUCCESS_CODE);
        String messageEn = RedisCache.getRespCode(Constants.SUCCESS_CODE).getValueEN();
        String messageKh = RedisCache.getRespCode(Constants.SUCCESS_CODE).getValueKH();

        if (Constants.ENGLISH.equalsIgnoreCase(userLang)) {
            this.setMessage(messageEn);
        } else {
            this.setMessage(messageKh);
        }
        return this;
    }

    public MessageResponse setSuccess(MessageResponse dataObj, String userLang) {
        this.setStatus(Constants.SUCCESS_CODE);
        String messageEn = RedisCache.getRespCode(Constants.SUCCESS_CODE).getValueEN();
        String messageKh = RedisCache.getRespCode(Constants.SUCCESS_CODE).getValueKH();

        if (Constants.ENGLISH.equalsIgnoreCase(userLang)) {
            this.setMessage(messageEn);
        } else {
            this.setMessage(messageKh);
        }
        this.setData(dataObj.getData());
        return this;
    }
}

package com.one.onegate.exception;

import com.one.onegate.startup.RedisCache;
import com.one.onegate.utils.Constants;
import com.one.onegate.utils.MessageResponse;
import org.springframework.http.HttpStatus;

public class AppException extends Exception {

    private MessageResponse responseMessage;
    private HttpStatus httpStatus;
    private String errorCode;
    private String userLang;

    public AppException(String code, String lang) {
        super();
        this.errorCode = code;
        this.userLang = lang;
    }

    // Always return with 200 status even if it's still an error
    public HttpStatus getHttpStatus() {
        if (this.httpStatus == null) {
            this.httpStatus = HttpStatus.OK;
        }
        return httpStatus;
    }

    public MessageResponse getErrorResponse(Object object, String userLang) {
        String messageEn = RedisCache.getRespCode(errorCode).getValueEN();
        String messageKh = RedisCache.getRespCode(errorCode).getValueKH();
        MessageResponse message = new MessageResponse();
        if (Constants.ENGLISH.equalsIgnoreCase(userLang)) {
            message.setMessage(messageEn);
        } else {
            message.setMessage(messageKh);
        }

        switch (RedisCache.getRespCode(errorCode).getHttpStatus()) {
            case "200":
                this.httpStatus = HttpStatus.OK;
                break;
            case "400":
                this.httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                break;
        }
        message.setStatus(errorCode);
        return message;
    }
}
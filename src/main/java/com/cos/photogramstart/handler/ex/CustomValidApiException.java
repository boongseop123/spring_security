package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidApiException extends RuntimeException {
    private static final long serialVersionUID=1L;

    private Map<String, String> errorMap;


    public CustomValidApiException(String message){
        super(message);
        this.errorMap=errorMap;
    }

    public Map<String, String> getErrorMap(){
        return errorMap;
    }
}


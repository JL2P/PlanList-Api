package com.api.planlist.common.model.exception;

public class CommonCustomException extends Exception{

    public CommonCustomException() {
        super("already exist");
    }

    public CommonCustomException(String massage) {
        super(massage+" already exist ");
    }
}

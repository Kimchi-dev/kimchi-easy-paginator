package me.kimchi.pagination.exception;

import me.kimchi.pagination.constant.PagingExceptConstant;

public class PagingException extends RuntimeException{

    private PagingExceptConstant constant;

    public PagingException(PagingExceptConstant constant) {
        this.constant = constant;
    }

    @Override
    public String getMessage() {
        return this.constant.getEMessage();
    }

    public int getCode() {
        return this.constant.getECode();
    }
    
    public PagingExceptConstant getConstant() {
        return this.constant;
    }
}

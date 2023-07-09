package kr.devis.util.offsetpaginator.pagination.exception;

import kr.devis.util.offsetpaginator.pagination.constant.PagingExceptConstant;

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

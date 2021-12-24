package com.github.kimchidev.pagination.paginator;


import com.github.kimchidev.pagination.constant.PaginatorConstant;
import com.github.kimchidev.pagination.object.PaginationObject;

abstract class AbstractDefaultPaginator implements Paginator {

    private PaginatorConstant constant = PaginatorConstant.MYSQL_PAGING;

    private double resultTime;

    public final void setPagingObject(PaginationObject object, PaginatorConstant constant) throws Exception {
        setPagingObject(object);
        validateAndInitializeConstant(constant);
    }

    protected abstract void setPagingObject(PaginationObject object);

    protected abstract void validateAndInitializeConstant(PaginatorConstant constant) throws Exception;

    protected void setConstant(PaginatorConstant constant) {
        this.constant = constant;
    }

    public final PaginatorConstant getCurrentConstant() {
        return this.constant;
    }

    @Override
    public double getResultTime() {
        return this.resultTime;
    }

    public void setResultTime(double resultTime) {
        this.resultTime = resultTime;
    }
}

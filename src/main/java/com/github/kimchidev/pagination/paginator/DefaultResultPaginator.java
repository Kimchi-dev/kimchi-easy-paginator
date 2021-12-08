package com.github.kimchidev.pagination.paginator;

import com.github.kimchidev.pagination.calculator.Calculator;
import com.github.kimchidev.pagination.object.PaginatedObject;

public class DefaultResultPaginator implements ResultPaginator {
    private Calculator calculator;
    private PaginatedObject object;

    public DefaultResultPaginator(PaginatedObject object) {
        this.object = object;
    }
    @Override
    public PaginatedObject paginate() {
        return this.object;
    }

}

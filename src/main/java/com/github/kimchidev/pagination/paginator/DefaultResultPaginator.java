package com.github.kimchidev.pagination.paginator;

import com.github.kimchidev.pagination.calculator.Calculator;
import com.github.kimchidev.pagination.object.PaginatedObject;

class DefaultResultPaginator implements ResultPaginator {
    private PaginatedObject object;

    public DefaultResultPaginator(PaginatedObject object) {
        this.object = object;
    }
    @Override
    public PaginatedObject paginate() {
        return this.object;
    }

}

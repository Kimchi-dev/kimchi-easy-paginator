package me.kimchi.pagination.paginator;

import me.kimchi.pagination.calculator.Calculator;
import me.kimchi.pagination.object.PaginatedObject;

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

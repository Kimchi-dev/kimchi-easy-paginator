package pagination.paginator;

import pagination.calculator.Calculator;
import pagination.object.PaginatedObject;

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

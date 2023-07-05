package kr.devis.util.offsetpaginator.paginator;

import kr.devis.util.offsetpaginator.object.PaginatedResult;

class DefaultResultPaginator implements ResultPaginator {
    private PaginatedResult object;

    public DefaultResultPaginator(PaginatedResult object) {
        this.object = object;
    }
    @Override
    public PaginatedResult paginate() {
        return this.object;
    }

}

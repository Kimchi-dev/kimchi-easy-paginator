package kr.devis.util.offsetpaginator.paginator;

import kr.devis.util.offsetpaginator.object.PaginatedObject;

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

package kr.devis.util.offsetpaginator.pagination.paginator;

import kr.devis.util.offsetpaginator.pagination.constant.PaginatorConstant;
import kr.devis.util.offsetpaginator.pagination.constant.PagingOption;

public interface Paginator {

    PagingOption getPagingOption() throws Exception;

    void init(int totalContentsCount, int numPerPage, int contentsPerPage, int currentPage, PaginatorConstant constant) throws Exception;

    Paginator elastic() throws Exception;

    Paginator fixed() throws Exception;

    Paginator move(boolean isPre, boolean isNext) throws Exception;

    ResultPaginator build() throws Exception;

    double getResultTime();

    String getPagingLog();
}

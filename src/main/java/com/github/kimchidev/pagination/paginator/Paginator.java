package com.github.kimchidev.pagination.paginator;

import com.github.kimchidev.pagination.constant.PaginatorConstant;
import com.github.kimchidev.pagination.constant.PagingOption;

public interface Paginator {

    PagingOption getPagingOption() throws Exception;

    void init(int totalContentsCount, int numPerPage, int contentsPerPage, int currentPage, PaginatorConstant constant) throws Exception;

    Paginator elastic() throws Exception;

    Paginator fixed() throws Exception;

    Paginator pre() throws Exception;

    Paginator next() throws Exception;

    ResultPaginator build() throws Exception;

    double getResultTime();

    String getPagingLog();
}

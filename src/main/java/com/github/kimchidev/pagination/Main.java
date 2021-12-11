package com.github.kimchidev.pagination;

import com.github.kimchidev.pagination.constant.PaginatorConstant;
import com.github.kimchidev.pagination.paginator.KimchiPaginator;

public class Main {
    public static void main(String[] args) throws Exception {
        KimchiPaginator paginator = new KimchiPaginator();
        int currentPage = 15;
        paginator.init(150, 7,10, currentPage, PaginatorConstant.MYSQL_PAGING);
        paginator.elastic().build();
        String pagingLog = paginator.getPagingLog();
        System.out.println(pagingLog);
    }
}

package com.github.kimchidev.pagination;

import com.github.kimchidev.pagination.constant.PaginatorConstant;
import com.github.kimchidev.pagination.maker.PageMaker;
import com.github.kimchidev.pagination.object.PaginatedObject;
import com.github.kimchidev.pagination.paginator.OffsetPaginator;
import com.github.kimchidev.pagination.paginator.ResultPaginator;

public class Main {

    public static void main(String[] args) throws Exception {

        boolean isPre = false;
        boolean isNext = false;


        OffsetPaginator paginator = new OffsetPaginator(21059430, 183742);
        paginator.init(21059430, 11, 17, 183742, PaginatorConstant.POSTGRESQL_PAGING);
        PaginatedObject paginate = paginator
                .elastic()
                .move(isPre, isNext)
                .build()
                .paginate();
        String log = paginator.getPagingLog();

        System.out.println(log);

    }
}

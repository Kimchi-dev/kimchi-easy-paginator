package com.github.kimchidev.pagination;

import com.github.kimchidev.pagination.maker.PageMaker;
import com.github.kimchidev.pagination.object.PaginatedObject;
import com.github.kimchidev.pagination.paginator.OffsetPaginator;

public class Main {

    public static void main(String[] args) throws Exception {

        boolean isPre = false;
        boolean isNext = false;

        PaginatedObject result = new OffsetPaginator(150, 11)
                .elastic()
                .move(isPre, isNext)
                .build()
                .paginate();

        String generated = new PageMaker(result, "/board/list")
                .setMoveButtonName("이전", "다음")
                .exposeDisabled()
                .html().css().get();
    }
}

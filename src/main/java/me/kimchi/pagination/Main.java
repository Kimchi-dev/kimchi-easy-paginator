package me.kimchi.pagination;

import me.kimchi.pagination.constant.PaginatorConstant;
import me.kimchi.pagination.object.PaginatedObject;
import me.kimchi.pagination.paginator.KimchiPaginator;

public class Main {
    public static void main(String[] args) throws Exception {

        /* Given */
        KimchiPaginator paginator = new KimchiPaginator();
        int currentPage = 4;
        /* When */
        paginator.init(40, 10, 10, currentPage, PaginatorConstant.MYSQL_PAGING);
        PaginatedObject result = paginator.fixed().build().paginate();
        String pagingLog = paginator.getPagingLog();

        System.out.println(pagingLog);
    }
}

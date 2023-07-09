package kr.devis.util.offsetpaginator;

import kr.devis.util.offsetpaginator.pagination.constant.PaginatorConstant;
import kr.devis.util.offsetpaginator.pagination.paginator.OffsetPaginator;

public class Main {

    public static void main(String[] args) throws Exception {

        boolean isPre = false;
        boolean isNext = false;


        OffsetPaginator paginator = new OffsetPaginator(21059430, 183742);
        paginator.init(21059430, 11, 17, 183742, PaginatorConstant.POSTGRESQL_PAGING);
        paginator
                .elastic()
                .move(isPre, isNext)
                .build()
                .paginate();

        System.out.println(paginator.getPagingLog());

    }
}

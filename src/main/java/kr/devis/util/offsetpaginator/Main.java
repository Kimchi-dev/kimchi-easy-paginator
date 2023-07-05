package kr.devis.util.offsetpaginator;

import kr.devis.util.entityprinter.print.EntityPrinter;
import kr.devis.util.entityprinter.print.PrintConfigurator;
import kr.devis.util.offsetpaginator.constant.PaginatorConstant;
import kr.devis.util.offsetpaginator.object.PaginatedResult;
import kr.devis.util.offsetpaginator.paginator.OffsetPaginator;

public class Main {

    public static void main(String[] args) throws Exception {

        boolean isPre = false;
        boolean isNext = false;


        OffsetPaginator paginator = new OffsetPaginator(21059430, 183742);
        paginator.init(21059430, 11, 17, 183742, PaginatorConstant.POSTGRESQL_PAGING);
        PaginatedResult paginate = paginator
                .elastic()
                .move(isPre, isNext)
                .build()
                .paginate();
        String log = paginator.getPagingLog();
        EntityPrinter printer = new EntityPrinter();
        PrintConfigurator<Object> configured = PrintConfigurator.create()
                .activateFields(1, 3, 5, 7)
                .excludeDataType();

        System.out.println(printer.draw(paginate, configured));

    }
}

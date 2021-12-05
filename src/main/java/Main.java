import me.kimchi.pagination.constant.PaginatorConstant;
import me.kimchi.pagination.maker.PagingMaker;
import me.kimchi.pagination.object.PaginatedObject;
import me.kimchi.pagination.paginator.KimchiPaginator;

public class Main {
    public static void main(String[] args) throws Exception {
        int currentPage = 1;
        KimchiPaginator paginator = new KimchiPaginator();

        paginator.init(254, 6, 7, currentPage, PaginatorConstant.MYSQL_PAGING);
        PaginatedObject result = paginator.elastic()
                .build()
                .paginate();

        PagingMaker maker = new PagingMaker(result, "/board/list", true);
        maker.html().withCss().generate().download("/Users/jason/Documents/pagination/sample.html");

        String pagingLog = paginator.getPagingLog();

        System.out.println(pagingLog);
    }
}

package pagination.paginator;

import pagination.object.PaginationObject;
import pagination.constant.PagingOption;
import pagination.constant.PaginatorConstant;

/**
 * <b>Since 11. 2021 by Kimchi-dev</b>
 * <hr>
 * Implementation Proxy of Paginator
 * <p>This Proxy just supply paginator with DefaultPaginator</p>
 */
public class KimchiPaginator implements Paginator {

    private Paginator paginator;

    public KimchiPaginator (){
        paginator = new DefaultPaginator();
    }

    @Override
    public PagingOption getPagingOption() {
        return paginator.getPagingOption();
    }

    @Override
    public void setPagingObject(PaginationObject object, PaginatorConstant constant) throws Throwable {
        paginator.setPagingObject(object, constant);
    }

    /**
     *
     * @param totalContentsCount Number of total contents - Assign Your board list.size()
     * @param numPerPage Number of page-number per page - [1][2][3][4][5]
     * @param contentsPerPage  Number of contents(board) per page;
     * @param currentPage currently page number
     * @param constant Select Your Database. default PaginatorConstant.MYSQL_PAGING
     * @throws Throwable
     */
    @Override
    public void init(int totalContentsCount, int numPerPage, int contentsPerPage, int currentPage, PaginatorConstant constant) throws Throwable {
        paginator.init(totalContentsCount, numPerPage, contentsPerPage, currentPage, constant);
    }

    @Override
    public Paginator elastic() throws Throwable {
        return paginator.elastic();
    }

    @Override
    public Paginator fixed() throws Throwable {
        return paginator.fixed();
    }

    @Override
    public Paginator pre() throws Throwable {
        return paginator.pre();
    }

    @Override
    public Paginator next() throws Throwable {
        return paginator.next();
    }

    @Override
    public ResultPaginator build()throws Throwable {
        return paginator.build();
    }

    @Override
    public String getPagingLog() {
        return paginator.getPagingLog();
    }

    @Override
    public double getResultTime() {
        return paginator.getResultTime();
    }
}

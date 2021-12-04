package pagination.paginator;


import javafx.scene.control.Pagination;
import pagination.object.PaginationObject;
import pagination.constant.PaginatorConstant;

public abstract class AbstractDefaultPaginator implements Paginator {

    private PaginatorConstant constant = PaginatorConstant.MYSQL_PAGING;

    private double resultTime;

    @Override
    public final void setPagingObject(PaginationObject object, PaginatorConstant constant) throws Throwable {
        setPagingObject(object);
        validateAndInitializeConstant(constant);
    }

    protected abstract void setPagingObject(PaginationObject object);

    protected abstract void validateAndInitializeConstant(PaginatorConstant constant) throws Throwable;

    protected void setConstant(PaginatorConstant constant) {
        this.constant = constant;
    }

    public final PaginatorConstant getCurrentConstant() {
        return this.constant;
    }

    @Override
    public double getResultTime() {
        return this.resultTime;
    }

    public void setResultTime(double resultTime) {
        this.resultTime = resultTime;
    }
}

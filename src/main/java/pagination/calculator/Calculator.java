package pagination.calculator;

import pagination.constant.PagingOption;
import pagination.object.PaginatedObject;
import pagination.object.PaginationObject;

import java.util.Map;

public interface Calculator {

    void setting(PaginationObject object, boolean goPre, boolean goNext);

    void moveNext(boolean goNext);

    void movePre(boolean goPre);

    void calculate(PaginationObject object) throws Throwable;

    void applyOption(PagingOption option) throws Throwable;

    PaginatedObject getResult();

    Map<String , Integer> getCalculatedData();
}

package kr.devis.util.offsetpaginator.pagination.calculator;

import kr.devis.util.offsetpaginator.pagination.constant.PagingOption;
import kr.devis.util.offsetpaginator.pagination.object.PaginatedResult;
import kr.devis.util.offsetpaginator.pagination.object.PaginationObject;

import java.util.Map;

public interface Calculator {

    void setting(PaginationObject object, boolean goPre, boolean goNext);

    void moveNext(boolean goNext);

    void movePre(boolean goPre);

    void calculate(PaginationObject object) throws Exception;

    void applyOption(PagingOption option) throws Exception;

    PaginatedResult getResult();

    Map<String , Integer> getCalculatedData();
}

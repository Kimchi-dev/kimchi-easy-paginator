package kr.devis.util.offsetpaginator.calculator;

import kr.devis.util.offsetpaginator.constant.PagingOption;
import kr.devis.util.offsetpaginator.object.PaginatedResult;
import kr.devis.util.offsetpaginator.object.PaginationObject;

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

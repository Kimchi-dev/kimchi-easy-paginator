package me.kimchi.pagination.calculator;

import me.kimchi.pagination.constant.PagingOption;
import me.kimchi.pagination.object.PaginatedObject;
import me.kimchi.pagination.object.PaginationObject;

import java.util.Map;

public interface Calculator {

    void setting(PaginationObject object, boolean goPre, boolean goNext);

    void moveNext(boolean goNext);

    void movePre(boolean goPre);

    void calculate(PaginationObject object) throws Exception;

    void applyOption(PagingOption option) throws Exception;

    PaginatedObject getResult();

    Map<String , Integer> getCalculatedData();
}

package com.github.kimchidev.pagination.calculator;

import com.github.kimchidev.pagination.constant.PagingOption;
import com.github.kimchidev.pagination.object.PaginatedObject;
import com.github.kimchidev.pagination.object.PaginationObject;

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

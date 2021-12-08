package com.github.kimchidev.pagination.calculator;

import com.github.kimchidev.pagination.constant.PagingOption;
import com.github.kimchidev.pagination.object.PaginatedObject;
import com.github.kimchidev.pagination.object.PaginationObject;
import com.github.kimchidev.pagination.constant.CalculateConstant;
import com.github.kimchidev.pagination.object.DefaultPaginationObject;

import java.util.Map;

public final class DefaultCalculator implements Calculator {

    private IndexCalculator indexCalculator;
    private AbstractCommonCalculator calculator;
    private PaginationObject object;

    public DefaultCalculator(IndexCalculator indexCalculator) {
        this.indexCalculator = indexCalculator;
    }

    @Override
    public void applyOption(PagingOption option)  throws Exception {
        this.calculator = AppropriateCalculatorProvider.getInstance().findCalculatorByOption(option);
    }

    @Override
    public void setting(PaginationObject object, boolean goPre, boolean goNext) {
        calculator.init(goPre, goNext);
    }

    @Override
    public void moveNext(boolean goNext) {
        calculator.setGoNext(goNext);
    }

    @Override
    public void movePre(boolean goPre) {
        calculator.setGoPre(goPre);
    }

    @Override
    public void calculate(PaginationObject object) throws Exception {
        DefaultPaginationObject defaultObject = (DefaultPaginationObject) object;
        calculator.addValue(CalculateConstant.CURRENT_PAGE, defaultObject.getCurrentPage());
        calculator.addValue(CalculateConstant.CONTENTS_PER_PAGE, defaultObject.getContentsPerPage());
        calculator.addValue(CalculateConstant.NUM_SIZE_PER_PAGE, defaultObject.getNumSizePerPage());
        calculator.addValue(CalculateConstant.TOTAL_CONTENTS_COUNT, defaultObject.getTotalContentsCount());

        calculator.addValue(CalculateConstant.TOTAL_PAGE_COUNT, calculator.calTotalPageCount(calculator.getValue(CalculateConstant.TOTAL_CONTENTS_COUNT), calculator.getValue(CalculateConstant.CONTENTS_PER_PAGE)));
        calculator.addValue(CalculateConstant.CURRENT_STEP, calculator.calCurrentStep(calculator.getValue(CalculateConstant.TOTAL_PAGE_COUNT), calculator.getValue(CalculateConstant.CURRENT_PAGE), calculator.getValue(CalculateConstant.NUM_SIZE_PER_PAGE)));
        calculator.addValue(CalculateConstant.TOTAL_STEP_CNT, calculator.calTotalStep(calculator.getValue(CalculateConstant.TOTAL_PAGE_COUNT), calculator.getValue(CalculateConstant.NUM_SIZE_PER_PAGE)));

        calculator.calMove(defaultObject, calculator, calculator.getValue(CalculateConstant.CURRENT_STEP), calculator.getValue(CalculateConstant.TOTAL_STEP_CNT), calculator.getValue(CalculateConstant.NUM_SIZE_PER_PAGE));

        calculator.addValue(CalculateConstant.START_PAGE, calculator.calStartPage(calculator.getValue(CalculateConstant.TOTAL_PAGE_COUNT), calculator.getValue(CalculateConstant.CURRENT_PAGE), calculator.getValue(CalculateConstant.NUM_SIZE_PER_PAGE), calculator.getValue(CalculateConstant.CURRENT_STEP)));
        calculator.addValue(CalculateConstant.END_PAGE,calculator.calEndPage(calculator.getValue(CalculateConstant.TOTAL_PAGE_COUNT), calculator.getValue(CalculateConstant.CURRENT_PAGE), calculator.getValue(CalculateConstant.NUM_SIZE_PER_PAGE), calculator.getValue(CalculateConstant.CURRENT_STEP)));
        calculator.addValue(CalculateConstant.START_INDEX, indexCalculator.calStartIndex(calculator.getValue(CalculateConstant.CURRENT_PAGE), calculator.getValue(CalculateConstant.NUM_SIZE_PER_PAGE)));
        calculator.addValue(CalculateConstant.END_INDEX, indexCalculator.calEndIndex(calculator.getValue(CalculateConstant.CURRENT_PAGE), calculator.getValue(CalculateConstant.NUM_SIZE_PER_PAGE), calculator.getValue(CalculateConstant.START_INDEX), calculator.getValue(CalculateConstant.CONTENTS_PER_PAGE)));
        calculator.calAbleToNext(defaultObject);
        defaultObject.setCalculatedResult(calculator.getValue(CalculateConstant.TOTAL_PAGE_COUNT), calculator.getValue(CalculateConstant.CURRENT_STEP), calculator.getValue(CalculateConstant.START_PAGE), calculator.getValue(CalculateConstant.END_PAGE), calculator.getValue(CalculateConstant.TOTAL_STEP_CNT));
        defaultObject.setResult(calculator.getValue(CalculateConstant.START_INDEX), calculator.getValue(CalculateConstant.END_INDEX));
        this.object = defaultObject;
    }

    @Override
    public PaginatedObject getResult() {
        return this.object.convertToPaginatedObject();
    }

    @Override
    public Map<String, Integer> getCalculatedData() {
        return calculator.getCalculatedData();
    }
}

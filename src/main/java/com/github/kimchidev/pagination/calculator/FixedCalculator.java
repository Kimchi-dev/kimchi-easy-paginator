package com.github.kimchidev.pagination.calculator;

import com.github.kimchidev.pagination.constant.CalculateConstant;
import com.github.kimchidev.pagination.object.PaginationObject;

class FixedCalculator extends AbstractCommonCalculator{

    @Override
    public final int calTotalStep(int totalPageCnt, int numSizePerPage) {
        return totalPageCnt;
    }

    @Override
    public final int calStartPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep) throws Exception {
        int pivot = ((int) Math.floor(((float) numSizePerPage) / 2));
        if(totalPageCnt <= numSizePerPage) {

            return 1;
        }
        pivot = numSizePerPage % 2 == 0 ? pivot - 1 : pivot;

        if (currentPage < (totalPageCnt - pivot)) {
            int expectedStart = (currentPage - pivot);
            if(expectedStart > 1) {

                return expectedStart;
            } else if (expectedStart <= 1) {
                return 1;
            }
        }

        return (totalPageCnt - (numSizePerPage - 1));
    }

    @Override
    public final int calEndPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep) {

        int pivot = ((int) Math.floor(((float) numSizePerPage) / 2));
        if (totalPageCnt <= numSizePerPage) {

            return totalPageCnt;
        }

        if(currentPage <= pivot) {

            return numSizePerPage;
        } else if(pivot < currentPage) {
            int expectedEnd = (currentPage + pivot);

            if(expectedEnd < totalPageCnt) {

                return expectedEnd;
            }
        }


        return totalPageCnt;
    }

    @Override
    public final int calCurrentStep(int totalPageCnt, int currentPage, int numSizePerPage) {
        return currentPage;
    }

    @Override
    protected void calMove(PaginationObject object, AbstractCommonCalculator calculator, int currentStep, int totalStepCnt, int numSizePerPage) throws Exception {

        int currentPage = super.getValue(CalculateConstant.CURRENT_PAGE);
        int totalPageCnt = super.getValue(CalculateConstant.TOTAL_PAGE_COUNT);

        if(currentPage <= totalStepCnt) {
            object.ableToStep(false, true);
        }

        if(1 < currentPage) {
            object.ableToStep(true, object.isAbleToNextStep());
        }

        if(super.isGoPre() && object.isAbleToPreviousStep()) {
            if((currentPage - numSizePerPage) <= 1) {

                super.addValue(CalculateConstant.CURRENT_PAGE, 1);
                super.addValue(CalculateConstant.CURRENT_STEP, 1);
            } else if ((currentPage - numSizePerPage) > 1) {

                super.addValue(CalculateConstant.CURRENT_PAGE, (currentPage - numSizePerPage));
                super.addValue(CalculateConstant.CURRENT_STEP, (currentPage - numSizePerPage));
            }
        } else if (super.isGoNext() && object.isAbleToNextStep()) {
            if((currentPage + numSizePerPage) >= totalPageCnt){

                super.addValue(CalculateConstant.CURRENT_PAGE, totalPageCnt);
                super.addValue(CalculateConstant.CURRENT_STEP, totalPageCnt);
            }
            else if ((currentPage + numSizePerPage) < totalPageCnt) {

                super.addValue(CalculateConstant.CURRENT_PAGE, (currentPage + numSizePerPage));
                super.addValue(CalculateConstant.CURRENT_STEP, (currentPage + numSizePerPage));
            }
        }

        if(super.getValue(CalculateConstant.CURRENT_PAGE) < totalStepCnt) {
            object.ableToStep(false, true);
        } else {
            object.ableToStep(false, false);
        }

        if(1 < super.getValue(CalculateConstant.CURRENT_PAGE)) {
            object.ableToStep(true,  object.isAbleToNextStep());
        }

        object.setCurrentStepAndPage(super.getValue(CalculateConstant.CURRENT_STEP), super.getValue(CalculateConstant.CURRENT_PAGE));
    }
}

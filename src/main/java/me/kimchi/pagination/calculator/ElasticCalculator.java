package me.kimchi.pagination.calculator;

import me.kimchi.pagination.object.PaginationObject;
import me.kimchi.pagination.constant.CalculateConstant;

public class ElasticCalculator extends AbstractCommonCalculator{

    @Override
    protected int calTotalStep(int totalPageCnt, int numSizePerPage) {
        int totalStep = (totalPageCnt / numSizePerPage) + (totalPageCnt % numSizePerPage == 0 ? 0 : 1);
        return totalStep;
    }

    @Override
    protected int calStartPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep) throws Exception {
        if(currentPage > totalPageCnt) {
            throw new IllegalArgumentException("Illegal arguments ! current page cannot excess total page count.");
        }

        if(currentPage <= numSizePerPage) {
            return 1;
        }

        int startPage = (currentStep - 1) * numSizePerPage + 1;

        return startPage;
    }

    @Override
    protected int calEndPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep) {
        if(currentPage > totalPageCnt) {
            throw new IllegalArgumentException("illegal arguments ! current page cannot excess total page count.");
        }

        if(currentPage <= numSizePerPage) {
            return numSizePerPage;
        }

        int endPage = (currentStep * numSizePerPage);
        if(endPage > totalPageCnt) {
            endPage = totalPageCnt;
        }
        return endPage;
    }

    @Override
    protected int calCurrentStep(int totalPageCnt, int currentPage, int numSizePerPage) {
        if(currentPage > totalPageCnt) {
            throw new IllegalArgumentException("illegal arguments ! current page cannot excess total page count.");
        }

        if(currentPage <= numSizePerPage) {
            return 1;
        }
        int currentStep = (currentPage / numSizePerPage) + (currentPage % numSizePerPage == 0 ? 0 : 1);

        return currentStep;
    }

    @Override
    protected void calMove(PaginationObject object, AbstractCommonCalculator calculator, int currentStep, int totalStepCnt, int numSizePerPage) throws Exception {

        if(currentStep < totalStepCnt) {
            object.ableToStep(false, true);
        }

        if(1 < currentStep) {
            object.ableToStep(true,  object.isAbleToNextStep());
        }


        if(super.isGoPre() && object.isAbleToPreviousStep()) {
            int currentPage = (currentStep * numSizePerPage) - (numSizePerPage);
            super.addValue(CalculateConstant.CURRENT_PAGE, currentPage);
            currentStep -= 1;
            super.addValue(CalculateConstant.CURRENT_STEP, currentStep);
        }

        if(super.isGoNext() && object.isAbleToNextStep()) {
            int currentPage = (currentStep * numSizePerPage) + 1;
            super.addValue(CalculateConstant.CURRENT_PAGE, currentPage);
            currentStep += 1;
            super.addValue(CalculateConstant.CURRENT_STEP, currentStep);
        }


        if(super.getValue(CalculateConstant.CURRENT_STEP) < totalStepCnt) {
            object.ableToStep(false, true);
        } else {
            object.ableToStep(false, false);
        }

        if(1 < super.getValue(CalculateConstant.CURRENT_STEP)) {
            object.ableToStep(true,  object.isAbleToNextStep());
        }
        object.setCurrentStepAndPage(super.getValue(CalculateConstant.CURRENT_STEP), super.getValue(CalculateConstant.CURRENT_PAGE));
    }

}

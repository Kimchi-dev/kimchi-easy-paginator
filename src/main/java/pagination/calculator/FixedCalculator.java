package pagination.calculator;

import pagination.constant.CalculateConstant;
import pagination.object.PaginationObject;

public class FixedCalculator extends AbstractCommonCalculator{

    @Override
    protected int calTotalStep(int totalPageCnt, int numSizePerPage) {
        return totalPageCnt;
    }

    @Override
    protected int calStartPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep) throws Throwable {
        int minimum = (numSizePerPage / 2) + (numSizePerPage % 2);
        if(totalPageCnt <= numSizePerPage) {
            if(minimum >= currentPage) {
                return 1;
            }
        }

        if(minimum >= currentPage) {
            return 1;
        }

        return (currentPage - minimum) + 1;

    }

    @Override
    protected int calEndPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep) {

        int pivot = ((int) Math.floor(((float) numSizePerPage) / 2));
        if (totalPageCnt <= numSizePerPage) {

            return totalPageCnt;
        }

        if((currentPage + (pivot)) <= totalPageCnt) {

            return (currentPage + pivot);
        } else if(currentPage + pivot > totalPageCnt) {

            return totalPageCnt;

        }

        return numSizePerPage;
    }

    @Override
    protected int calCurrentStep(int totalPageCnt, int currentPage, int numSizePerPage) {
        return currentPage;
    }

    @Override
    protected void calMove(PaginationObject object, AbstractCommonCalculator calculator, int currentStep, int totalStepCnt, int numSizePerPage) throws Throwable {

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

package kr.devis.util.offsetpaginator.object;

import kr.devis.util.offsetpaginator.validator.CommonValidator;

public abstract class AbstractPaginationObject implements PaginationObject {


    private int totalContentsCount;
    /**
     * Number of page numbers ,which is exposing at page.
     * <hr>
     * ex) 10
     * <p><b>[1][2][3][4][5][6][7][8][9][10]</b></p>
     */
    private int numSizePerPage;
    /**
     * Number of contents ,which is exposing at page.
     * <hr>
     * ex) 4
     * <p><b>[1][ some of title 1..]</b></p>
     * <p><b>[2][ some of title 2..]</b></p>
     * <p><b>[3][ some of title 3..]</b></p>
     * <p><b>[4][ some of title 4..]</b></p>
     */
    private int contentsPerPage;
    /**
     * Currently page number.
     */
    private int currentPage;

    private boolean ableToNextStep = false;

    private boolean ableToPreviousStep = false;

    private boolean hasNextPage = false;

    private CommonValidator validator = new CommonValidator();

    private int totalPageCnt;
    private int totalStepCnt;
    private int currentStep;
    private int startPage;
    private int endPage;



    @Override
    public final PaginationObject initialize(int totalContentsCount, int numPerPage, int contentsPerPage, int currentPage) throws Exception {
        /* Initialize currently page number with first request */
        if(currentPage == 0) {
            currentPage = 1;
        }

        validator.validatePositive(totalContentsCount, numPerPage, contentsPerPage, currentPage);

        this.totalContentsCount = totalContentsCount;
        this.numSizePerPage = numPerPage;
        this.contentsPerPage = contentsPerPage;
        this.currentPage = currentPage;
        return this;
    }

    @Override
    public final PaginationObject next() {
        return this;
    }

    @Override
    public final PaginationObject pre() {
        return this;
    }


    public final void setCalculatedResult(int totalPageCnt, int currentStep, int startPage, int endPage, int totalStepCnt) {
        this.totalPageCnt = totalPageCnt;
        this.currentStep = currentStep;
        this.startPage = startPage;
        this.endPage = endPage;
        this.totalStepCnt = totalStepCnt;
    }
    @Override
    public final boolean isAbleToNextStep() {
        return this.ableToNextStep;
    }
    @Override
    public final boolean isAbleToPreviousStep() {
        return this.ableToPreviousStep;
    }

    @Override
    public final boolean hasNextPage() {
        return this.hasNextPage;
    }

    public final void setHasNextPage(boolean hasNextPage){
        this.hasNextPage = hasNextPage;
    }

    @Override
    public final void ableToStep(boolean ableToPreviousStep, boolean ableToNextStep) {
        this.ableToPreviousStep = ableToPreviousStep;
        this.ableToNextStep = ableToNextStep;
    }

    @Override
    public void setCurrentStepAndPage(int currentStep, int currentPage) {
        this.currentStep = currentStep;
        this.currentPage = currentPage;
    }

    @Override
    public final int getStartPage() {
        return startPage;
    }

    @Override
    public final int getEndPage() {
        return endPage;
    }

    public final int getCurrentPage() {
        return this.currentPage;
    }

    public final int getTotalContentsCount() {
        return this.totalContentsCount;
    }

    public final int getNumSizePerPage() {
        return this.numSizePerPage;
    }

    public final int getContentsPerPage() {
        return this.contentsPerPage;
    }

    public final int getTotalPageCnt() {
        return totalPageCnt;
    }

    public final int getCurrentStep() {
        return currentStep;
    }

}

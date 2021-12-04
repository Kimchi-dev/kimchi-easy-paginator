package pagination.calculator;

import pagination.object.PaginationObject;
import pagination.constant.CalculateConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * Common calculation formula
 */
public abstract class AbstractCommonCalculator {

    private Map<String, Integer> manager = new HashMap<>();
    private boolean goPre;
    private boolean goNext;

    public boolean isGoPre() {
        return this.goPre;
    }

    public boolean isGoNext() {
        return this.goNext;
    }

    public void setGoPre(boolean goPre) {
        this.goPre = goPre;
    }

    public void setGoNext(boolean goNext) {
        this.goNext = goNext;
    }

    public final void init( boolean goPre, boolean goNext) {
        this.goPre = goPre;
        this.goNext = goNext;
    }

    public final int calTotalPageCount(int totalContentsCount, int contentsPerPage) {

        int totalPageCount = (totalContentsCount / contentsPerPage) + (totalContentsCount % contentsPerPage == 0 ? 0 : 1);
        return totalPageCount;
    }

    protected abstract int calStartPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep) throws Throwable;

    protected abstract int calEndPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep);

    protected abstract int calCurrentStep(int totalPageCnt, int currentPage, int numSizePerPage);

    protected abstract int calTotalStep(int totalPageCnt, int numSizePerPage);

    protected abstract void calMove(PaginationObject object, AbstractCommonCalculator calculator, int currentStep, int totalStepCnt, int numSizePerPage) throws Throwable;

    protected void calAbleToNext(PaginationObject object) throws Throwable {
        int currentPage = this.getValue(CalculateConstant.CURRENT_PAGE);
        int totalPageCnt = this.getValue(CalculateConstant.TOTAL_PAGE_COUNT);

        if(currentPage < totalPageCnt) {
            object.setHasNextPage(true);
        }
    }

    protected final void addValue(CalculateConstant constant, Integer value) {
        String key = constant.getValueName();
        this.manager.put(key, value);
    }

    protected final int getValue(CalculateConstant constant) throws Throwable{

        Integer value = this.manager.get(constant.getValueName());
        if(value == null) {
            throw new NullPointerException("Not Found for value with this key. ["+constant.getValueName()+"]");
        }
        return value.intValue();
    }

    public Map<String, Integer> getCalculatedData() {
        return this.manager;
    }

}

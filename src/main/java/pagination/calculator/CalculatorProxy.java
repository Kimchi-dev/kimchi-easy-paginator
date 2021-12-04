package pagination.calculator;

import pagination.calculator.AppropriateCalculatorProvider;
import pagination.calculator.Calculator;
import pagination.constant.PagingOption;
import pagination.object.PaginatedObject;
import pagination.object.PaginationObject;
import pagination.constant.PaginatorConstant;

import java.util.Map;

public class CalculatorProxy implements Calculator {

    private Calculator calculator;

    public CalculatorProxy(PaginatorConstant constant) throws Throwable {
        this.calculator = AppropriateCalculatorProvider.getInstance().provide(constant);
    }

    @Override
    public void setting(PaginationObject object, boolean goPre, boolean goNext) {
        this.calculator.setting(object, goPre, goNext);
    }

    @Override
    public void moveNext(boolean goNext) {
        this.calculator.moveNext(goNext);
    }

    @Override
    public void movePre(boolean goPre) {
        this.calculator.movePre(goPre);
    }

    @Override
    public void calculate(PaginationObject object) throws Throwable {
        this.calculator.calculate(object);

    }

    @Override
    public void applyOption(PagingOption option) throws Throwable {
        this.calculator.applyOption(option);
    }

    @Override
    public PaginatedObject getResult() {
        return this.calculator.getResult();
    }

    @Override
    public Map<String, Integer> getCalculatedData() {
        return this.calculator.getCalculatedData();
    }
}

package me.kimchi.pagination.calculator;

import me.kimchi.pagination.constant.PagingOption;
import me.kimchi.pagination.paginator.Paginator;
import me.kimchi.pagination.constant.PaginatorConstant;

public class AppropriateCalculatorProvider {

    private static final AppropriateCalculatorProvider provider = new AppropriateCalculatorProvider();
    private Paginator paginator;
    private AppropriateCalculatorProvider(){
    }

    public static AppropriateCalculatorProvider getInstance() {
        return provider;
    }

    public Calculator provide(PaginatorConstant constant) throws Exception {

        if(constant.isSameConstant(PaginatorConstant.MYSQL_PAGING)) {

            return new DefaultCalculator(new IndexCalculator.MysqlIndexCalculator());
        } else if(constant.isSameConstant(PaginatorConstant.ORACLE_PAGING)) {
            return new DefaultCalculator(new IndexCalculator.OracleIndexCalculator());
        } else if (constant.isSameConstant(PaginatorConstant.POSTGRESQL_PAGING)) {
            return new DefaultCalculator((new IndexCalculator.OracleIndexCalculator()));
        }

        return null;
    }

    public AbstractCommonCalculator findCalculatorByOption(PagingOption option) throws Exception{

        if(option.isSameOption(PagingOption.FIXED)) {
            return new FixedCalculator();
        }
        else if(option.isSameOption(PagingOption.ELASTIC)) {
            return new ElasticCalculator();
        }else {
            throw new NullPointerException("There is no appropriate calculator by option["+option.getOptionName()+"]");
        }
    }
}

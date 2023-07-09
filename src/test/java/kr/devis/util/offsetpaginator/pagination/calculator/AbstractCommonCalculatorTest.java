package kr.devis.util.offsetpaginator.pagination.calculator;

import mock.MockBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractCommonCalculatorTest implements MockBase {

    @Mock
    private AbstractCommonCalculator calculator;


    @ParameterizedTest(name = "[공통 계산] 전체 페이지 개수 "+ParameterizedTest.DEFAULT_DISPLAY_NAME)
    @MethodSource("kr.devis.util.offsetpaginator.pagination.calculator.SourceAsMethod#commonTotalPageCount")
    public void calTotalPageCount(int totalContentsCount, int contentsPerPage, int expectedResult) throws Throwable {
        /* Given & When */
        int actualResult = calculator.calTotalPageCount(totalContentsCount, contentsPerPage);

        /* Then */
        assertEquals(expectedResult, actualResult);
    }

}

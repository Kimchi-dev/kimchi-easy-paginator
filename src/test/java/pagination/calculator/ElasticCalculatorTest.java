package pagination.calculator;

import me.kimchi.pagination.calculator.ElasticCalculator;
import mock.MockBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElasticCalculatorTest implements MockBase {

    @Mock
    private ElasticCalculator  calculator;

    @ParameterizedTest(name = "[엘라스틱 페이징] 전체 스탭 계산 "+ParameterizedTest.DEFAULT_DISPLAY_NAME)
    @MethodSource("pagination.calculator.SourceAsMethod#elasticTotalStep")
    public void calTotalStep(int totalPageCnt, int numSizePerPage, int expected) throws Throwable {
        /* Given & When */
        int actual = calculator.calTotalStep(totalPageCnt, numSizePerPage);

        /* Then */
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "[엘라스틱 페이징] 현재 스텝의 시작 페이지 계산 "+ParameterizedTest.DEFAULT_DISPLAY_NAME)
    @MethodSource("pagination.calculator.SourceAsMethod#elasticStartPage")
    public void calStartPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep, int expected) throws Throwable {
        /* Given & When */
        int actual = calculator.calStartPage(totalPageCnt, currentPage, numSizePerPage, currentStep);

        /* Then */
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "[엘라스틱 페이징] 현재 스텝의 마지막 페이지 계산 "+ParameterizedTest.DEFAULT_DISPLAY_NAME)
    @MethodSource("pagination.calculator.SourceAsMethod#elasticEndPage")
    public void calEndPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep, int expected) throws Throwable {
        /* Given & When */
        int actual = calculator.calEndPage(totalPageCnt, currentPage, numSizePerPage, currentStep);

        /* Then */
        assertEquals(expected, actual);
    }


    @ParameterizedTest(name = "[엘라스틱 페이징] 현재 스탭계산 "+ParameterizedTest.DEFAULT_DISPLAY_NAME)
    @MethodSource("pagination.calculator.SourceAsMethod#elasticCurrentStep")
    public void calCurrentStep(int totalPageCnt, int currentPage, int numSizePerPage, int expected) throws Throwable {
        /* Given & When */
        int actual = calculator.calCurrentStep(totalPageCnt, currentPage, numSizePerPage);

        /* Then */
        assertEquals(expected, actual);

    }

}

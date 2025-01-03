package kr.devis.util.offsetpaginator.pagination.calculator;

import mock.MockBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class FixedCalculatorTest implements MockBase {

    @Mock
    private FixedCalculator calculator;

    @ParameterizedTest(name = "[픽스드 페이징] 현재 스텝의 시작 페이지 계산 "+ParameterizedTest.DEFAULT_DISPLAY_NAME)
    @MethodSource("kr.devis.util.offsetpaginator.pagination.calculator.SourceAsMethod#fixedStartPage")
    public void calStartPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep, int expected) throws Throwable {
        /* Given & When */
        int actual = calculator.calStartPage(totalPageCnt, currentPage, numSizePerPage, currentStep);

        /* Then */
        assertEquals(expected, actual);

    }

    @ParameterizedTest(name = "[픽스드 페이징] 현재 스텝의 마지막 페이지 계산 "+ParameterizedTest.DEFAULT_DISPLAY_NAME)
    @MethodSource("kr.devis.util.offsetpaginator.pagination.calculator.SourceAsMethod#fixedEndPage")
    public void calEndPage(int totalPageCnt, int currentPage, int numSizePerPage, int currentStep, int expected) throws Throwable {
        /* Given & When */
        int actual = calculator.calEndPage(totalPageCnt, currentPage, numSizePerPage, currentStep);

        /* Then */
        assertEquals(expected, actual);

    }
}

package kr.devis.util.offsetpaginator.pagination.calculator;

import kr.devis.util.offsetpaginator.pagination.object.AbstractPaginationObject;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

class SourceAsMethod {

    /**
     * Test Arguments
     * <p>1. <b>totalPageCount</b></p>
     * <p>2. <b>numSizePerPage</b></p>
     * <p>3. <b>expected</b></p>
     * @return totalStepCount
     */
    public static Stream<Arguments> elasticTotalStep() {
        return Stream.of(
                Arguments.of(10, 20, 1),
                Arguments.of(157, 10, 16),
                Arguments.of(132, 6, 22),
                Arguments.of(17, 4, 5),
                Arguments.of(1, 12, 1),
                Arguments.of(0, 5, 1),
                Arguments.of(10, 20, 1),
                Arguments.of(10, 20, 1)
        );
    }

    /**
     * Test Arguments
     * <p>1. <b>totalPageCount</b></p>
     * <p>2. <b>currentPage</b></p>
     * <p>3. <b>numSizePerPage</b></p>
     * <p>4. <b>currentStep</b></p>
     * <p>5. <b>expected</b></p>
     * @return StartPage
     */
    public static Stream<Arguments> elasticStartPage() {
        return Stream.of(
                Arguments.of(1, 1, 10, 1, 1),
                Arguments.of(15, 5, 7, 1, 1),
                Arguments.of(23, 19, 3, 7, 19),
                Arguments.of(144, 81, 10, 9, 81),
                Arguments.of(2875, 1476, 17, 87, 1463),
                Arguments.of(989312, 988531, 55, 17974, 988516),
                Arguments.of(113843, 112927, 22, 5134, 112927)
        );
    }

    /**
     * Test Arguments
     * <p>1. <b>totalPageCount</b></p>
     * <p>2. <b>currentPage</b></p>
     * <p>3. <b>numSizePerPage</b></p>
     * <p>4. <b>currentStep</b></p>
     * <p>5. <b>expected</b></p>
     * @return EndPage
     */
    public static Stream<Arguments> elasticEndPage() {
        return Stream.of(
                Arguments.of(27, 18, 7, 3, 21),
                Arguments.of(64, 36, 9, 4, 36),
                Arguments.of(181, 18, 12, 2, 24),
                Arguments.of(185032, 184849, 18, 10270, 184860),
                Arguments.of(620312, 618841, 29, 21340, 618860),
                Arguments.of(892183, 772391, 44, 17555, 772420),
                Arguments.of(2014, 1992, 2, 996, 1992)

        );
    }

    /**
     * Test Arguments
     * <p>1. <b>totalPageCount</b></p>
     * <p>2. <b>currentPage</b></p>
     * <p>3. <b>numSizePerPage</b></p>
     * <p>4. <b>expected</b></p>
     * @return CurrentStep
     */
    public static Stream<Arguments> elasticCurrentStep() {
        return Stream.of(
                Arguments.of(1242, 211, 9, 24),
                Arguments.of(6092, 5920, 23, 258),
                Arguments.of(1, 1, 18, 1),
                Arguments.of(291, 171, 13, 14),
                Arguments.of(8932123, 8931519, 211, 42330),
                Arguments.of(24032, 21325, 17, 1255),
                Arguments.of(93932, 17283, 4, 4321)
        );
    }

    /**
     * Test Arguments
     * <p>1. <b>totalPageCount</b></p>
     * <p>2. <b>currentPage</b></p>
     * <p>3. <b>numSizePerPage</b></p>
     * <p>4. <b>currentStep</b></p>
     * <p>5. <b>expected</b></p>
     * @return StartPage
     */
    public static Stream<Arguments> fixedStartPage() {
        return Stream.of(
                Arguments.of(315, 248, 11, 23, 243),
                Arguments.of(2064, 1989, 10, 1989, 1985),
                Arguments.of(1205, 1204, 17, 1204, 1189),
                Arguments.of(20364, 20251, 14, 1447, 20245),
                Arguments.of(95431, 95364, 8, 11921, 95361),
                Arguments.of(1029353, 1029224, 16, 64327, 1029217)
        );
    }

    /**
     * Test Arguments
     * <p>1. <b>totalPageCount</b></p>
     * <p>2. <b>currentPage</b></p>
     * <p>3. <b>numSizePerPage</b></p>
     * <p>4. <b>currentStep</b></p>
     * <p>5. <b>expected</b></p>
     * @return EndPage
     */
    public static Stream<Arguments> fixedEndPage() {
        return Stream.of(
                Arguments.of(254, 150, 12, 13, 156),
                Arguments.of(2064, 1989, 4, 1989, 1991),
                Arguments.of(394213, 394212, 18, 394212, 394213),
                Arguments.of(1924, 1899, 10, 1899, 1904),
                Arguments.of(120, 74, 7, 74, 77),
                Arguments.of(12, 9, 10, 9, 12)
        );
    }


    /**
     * 해당메소드가 수행되기 이전에 {@link AbstractPaginationObject#initialize(int, int, int, int) AbstractPaginationObject.initialize()}에서
     * <p>validator를 호출하므로 contentsPerPage는 1미만의 값이 될 수 없음.</p>
     */
    public static Stream<Arguments> commonTotalPageCount() {
        return Stream.of(
                Arguments.of(0, 10, 1),
                Arguments.of(1, 1, 1),
                Arguments.of(17, 9, 2),
                Arguments.of(164, 8, 21),
                Arguments.of(2468, 13, 190),
                Arguments.of(705641, 687, 1028),
                Arguments.of(1990231, 13, 153095),
                Arguments.of(76392187, 15, 5092813),
                Arguments.of(2138921892, 10, 213892190),
                Arguments.of(932, 3, 311),
                Arguments.of(61, 5, 13)

        );
    }
}

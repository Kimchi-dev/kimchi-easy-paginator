package pagination.calculator;

import me.kimchi.pagination.object.AbstractPaginationObject;
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
     * @return StartPage
     */
    public static Stream<Arguments> elasticEndPage() {
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

package pagination.paginator;


import me.kimchi.pagination.calculator.DefaultCalculator;
import me.kimchi.pagination.constant.PaginatorConstant;
import me.kimchi.pagination.exception.PagingExceptConstant;
import me.kimchi.pagination.exception.PagingException;
import me.kimchi.pagination.object.PaginatedObject;
import me.kimchi.pagination.paginator.KimchiPaginator;
import me.kimchi.pagination.paginator.Paginator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginatorTest {

    @Test
    @DisplayName("세팅값 없이 elastic 메서드만 호출하여 Calculator 객체가 Null인 경우")
    public void elastic1() throws Throwable {
        /* Given */
        KimchiPaginator paginator = new KimchiPaginator();
        /* When */
        PagingException thrown = assertThrows(PagingException.class, () -> paginator.elastic().build().paginate());

        /* Then */
        assertEquals(PagingExceptConstant.DO_INIT_FOR_CALCULATOR.getECode(), thrown.getCode());

    }

    @Test
    @DisplayName("옵션을 설정하지 않은 상태로 빌드를 진행한 경우")
    public void elastic2() throws Throwable {
        /* Given */
        KimchiPaginator paginator = new KimchiPaginator();

        /* When */
        paginator.init(40, 10, 10, 1, PaginatorConstant.MYSQL_PAGING);
        PagingException thrown = assertThrows(PagingException.class, () -> paginator.build().paginate());

        /* Then */
        assertEquals(PagingExceptConstant.PROCEED_OPTION_AND_BUILD.getECode(), thrown.getCode());

    }

    @Test
    @DisplayName("elastic 페이징의 정상적인 범위로 들어오는지")
    public void elastic3() throws Throwable {
        /* Given */
        KimchiPaginator paginator = new KimchiPaginator();
        int currentPage = 1;
        /* When */
        paginator.init(40, 10, 10, currentPage, PaginatorConstant.MYSQL_PAGING);
        PaginatedObject result = paginator.elastic().build().paginate();

        /* Then */
        assertEquals(1, result.getCurrentPage());
        assertEquals(1, result.getStartPage());
        assertEquals(10, result.getEndPage());
    }

    @Test
    @DisplayName("세팅값 없이 fixed 메서드만 호출하여 Calculator 객체가 Null인 경우")
    public void fixed1() throws Throwable {
        /* Given */
        KimchiPaginator paginator = new KimchiPaginator();

        /* When */
        PagingException thrown = assertThrows(PagingException.class, () -> paginator.fixed().build().paginate());

        /* Then */
        assertEquals(PagingExceptConstant.DO_INIT_FOR_CALCULATOR.getECode(), thrown.getCode());

    }

    @Test
    @DisplayName("fixed 페이징의 정상적인 범위로 들어오는지")
    public void fixed2() throws Throwable {
        /* Given */
        KimchiPaginator paginator = new KimchiPaginator();
        int currentPage = 6;
        /* When */
        paginator.init(150, 10, 10, currentPage, PaginatorConstant.MYSQL_PAGING);
        PaginatedObject result = paginator.fixed().build().paginate();

        /* Then */
        assertEquals(currentPage, result.getCurrentPage());
        assertEquals(2, result.getStartPage());
        assertEquals(11, result.getEndPage());
    }

    @Test
    @DisplayName("현재페이지가 처음 또는 끝이 아니라면 이전 또는 다음 으로 갈 수 있는지")
    public void fixed3() throws Throwable {
        /* Given */
        KimchiPaginator paginator = new KimchiPaginator();

        /* When */
        paginator.init(150, 10, 10, 6, PaginatorConstant.MYSQL_PAGING);
        PaginatedObject result = paginator.fixed().build().paginate();

        /* Then */
        assertTrue(result.isAbleToPreStep());
        assertTrue(result.isAbleToNextStep());
    }
}

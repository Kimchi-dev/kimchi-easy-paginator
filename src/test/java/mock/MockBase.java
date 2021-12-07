package mock;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * mockito-core 와 mockito-junit-jupiter를 사용하는 경우 mockito-all을 제외해야한다.
 * mockito-all 에있는 Mockito클래스는 mockitoSession 메소드가 없기 때문에 mockito-junit-jupiter가 의존하고 있는 Mockito객체와 달라서, 예외가 발생한다.
 * 따라서 mockito-all 은 mockito-core와 같이 사용할 수 없다.
 */
@ExtendWith(MockitoExtension.class)
public interface MockBase {
}

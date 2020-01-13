package github.clyoudu.match;

import github.clyoudu.util.IdGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/13
 * @time 20:42
 * @desc MockitoFinalStaticTest
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoFinalStaticTest {

    @Mock
    private IdGenerator idGenerator;

    /**
     * Also, this error might show up because you use argument matchers with methods that cannot be mocked.
     * Following methods *cannot* be stubbed/verified: final/private/equals()/hashCode().
     * Mocking methods declared on non-public parent classes is not supported.
     */
    @Test
    public void testFinal () {
        Mockito.when(idGenerator.generated(Mockito.anyLong())).thenReturn(false);
        Assert.assertTrue(!idGenerator.generated(100L));
    }

    /**
     * Also, this error might show up because:
     * 1. you stub either of: final/private/equals()/hashCode() methods.
     *      Those methods *cannot* be stubbed/verified.
     *      Mocking methods declared on non-public parent classes is not supported.
     * 2. inside when() you don't call method on mock but on some other object.
     */
    @Test
    public void testStatic () {
        Mockito.when(IdGenerator.getId()).thenReturn(100L);
        Assert.assertEquals(new Long(100L), IdGenerator.getId());
    }

}

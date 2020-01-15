package github.clyoudu.asserts;

import github.clyoudu.util.IdGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author leichen
 * @date 2020/1/14 2:10 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(IdGenerator.class)
public class SimpleAssert {

    @Before
    public void before() {
        PowerMockito.mockStatic(IdGenerator.class);
    }

    @Test
    public void testStatic () {
        PowerMockito.when(IdGenerator.getId()).thenReturn(100L);
        Assert.assertEquals(new Long(100L), IdGenerator.getId());
    }

}

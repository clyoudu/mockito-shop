package github.clyoudu.match;

import github.clyoudu.util.IdGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/13
 * @time 20:42
 * @desc PowerMockitoTest
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(IdGenerator.class)
public class PowerMockitoTest {

    @Mock
    private IdGenerator idGenerator;

    @Spy
    private IdGenerator idGeneratorPower = new IdGenerator();

    @Before
    public void before() {
        PowerMockito.mockStatic(IdGenerator.class);
    }

    @Test
    public void testFinal () {
        PowerMockito.when(idGenerator.generated(Mockito.anyLong())).thenReturn(false);
        Assert.assertTrue(!idGenerator.generated(1L));

        PowerMockito.when(idGenerator.generated(Mockito.anyLong())).thenReturn(true);
        Assert.assertTrue(idGenerator.generated(1L));
    }

    @Test
    public void testStatic () {
        PowerMockito.when(IdGenerator.getId()).thenReturn(100L);
        Long id = IdGenerator.getId();

        PowerMockito.verifyStatic(IdGenerator.class, Mockito.times(1));
        IdGenerator.getId();
        Assert.assertEquals(new Long(100L), id);
    }

    @Test
    public void testPrivate () throws Exception {
        PowerMockito.doReturn(100L).when(idGeneratorPower, "getCurrentId1");
        Assert.assertEquals(new Long(100L), idGeneratorPower.getCurrentIdViaPrivate());
    }

    @Test
    public void testProtected () throws Exception {
        PowerMockito.doReturn(100L).when(idGeneratorPower, "getCurrentId2");
        Assert.assertEquals(new Long(100L), idGeneratorPower.getCurrentIdViaProtected());
    }

}

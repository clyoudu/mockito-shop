package github.clyoudu.match;

import github.clyoudu.dao.UserDao;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author leichen
 * @date 2020/7/21 10:57 上午
 * @since
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserDao.class)
public class PowerMockFinalTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Test
    public void testCount() {
        Mockito.when(userDao.count()).thenReturn(-100);
        Assert.assertEquals(0, userService.count());
    }

}

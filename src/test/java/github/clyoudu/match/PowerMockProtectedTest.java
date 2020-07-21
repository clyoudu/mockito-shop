package github.clyoudu.match;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author leichen
 * @date 2020/7/21 12:59 下午
 * @since
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserDao.class})
public class PowerMockProtectedTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Test
    public void testSearch() throws Exception {
        PowerMockito.when(userDao, "match2", Mockito.any(User.class), Mockito.anyString()).thenReturn(false);
        userDao.insert(new User("a", "a@a.a"));
        Assert.assertEquals(0, userService.search2("a").size());
    }
}

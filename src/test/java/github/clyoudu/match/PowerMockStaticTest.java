package github.clyoudu.match;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.util.IdGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author leichen
 * @date 2020/7/21 10:49 上午
 * @since
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({IdGenerator.class})
public class PowerMockStaticTest {

    private UserDao userDao = new UserDao();

    @Test
    public void testGetId() {
        PowerMockito.mockStatic(IdGenerator.class);
        Mockito.when(IdGenerator.getId()).thenReturn(10000L);
        User user = userDao.insert(new User("a", "b"));
        Assert.assertEquals(10000L, user.getId().longValue());
    }

}

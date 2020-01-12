package github.clyoudu.match;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 17:13
 * @desc NullArgumentMatcher
 */
@RunWith(MockitoJUnitRunner.class)
public class NullArgumentMatcher {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testNullable () {
        Mockito.when(userDao.insert(Mockito.nullable(User.class))).thenThrow(new MockitoException("test nullable()"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage(Is.is("test nullable()"));
        userService.insert(new User( "test", "test@qq.com"));
    }

    @Test
    public void testNull () {
        Mockito.when(userDao.delete(Mockito.isNull())).thenThrow(new MockitoException("test isNull()"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage(Is.is("test isNull()"));
        userService.delete(null);
    }

}

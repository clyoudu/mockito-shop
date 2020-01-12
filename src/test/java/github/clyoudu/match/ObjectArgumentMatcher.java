package github.clyoudu.match;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
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
 * @time 16:47
 * @desc ObjectArgumentMatcher
 */
@RunWith(MockitoJUnitRunner.class)
public class ObjectArgumentMatcher {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testAny () {
        Mockito.when(userDao.insert(Mockito.any())).thenThrow(new MockitoException("test any()"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage(Is.is("test any()"));
        userService.insert(new User( "test", "test@qq.com"));
    }

    @Test
    public void testAnyClass () {
        Mockito.when(userDao.insert(Mockito.any(User.class))).thenThrow(new MockitoException("test any(Class<T>)"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage(Is.is("test any(Class<T>)"));
        userService.insert(new User( "test", "test@qq.com"));
    }

    @Test
    public void testArgThat () {
        Mockito.when(userDao.insert(Mockito.argThat(new UsernameAndEmailMatcher()))).thenThrow(new MockitoException("test argThat()"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage(Is.is("test argThat()"));
        userService.insert(new User( "test11", "test@qq.com"));
    }

    @Test
    public void testRefEq () {
        User matchUser = new User(0L,"test", "test@qq.com");
        Mockito.when(userDao.insert(Mockito.refEq(matchUser, "id"))).thenThrow(new MockitoException("test refEq()"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage(Is.is("test refEq()"));
        userService.insert(new User( "test", "test@qq.com"));
    }

    @Test
    public void testEq () {
        User matchUser = new User("test", "test@qq.com");
        Mockito.when(userDao.insert(Mockito.eq(matchUser))).thenThrow(new MockitoException("test eq()"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage(Is.is("test eq()"));
        userService.insert(new User( "test", "test@qq.com"));
    }

    private static class UsernameAndEmailMatcher implements ArgumentMatcher<User> {
        @Override
        public boolean matches(User user) {
            return user.getUsername().contains("test") && user.getEmail().equals("test@qq.com");
        }
    }

}

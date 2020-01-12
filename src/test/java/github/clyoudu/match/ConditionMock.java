package github.clyoudu.match;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 18:10
 * @desc ConditionMock
 */
@RunWith(MockitoJUnitRunner.class)
public class ConditionMock {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    /**
     * java.lang.NullPointerException

     [MockitoHint] ConditionMock.testConditionReturn_error (see javadoc for MockitoHint):
     [MockitoHint] 1. Unused... -> at github.clyoudu.match.ConditionMock.testConditionReturn_error(ConditionMock.java:52)
     [MockitoHint]  ...args ok? -> at github.clyoudu.match.ConditionMock.testConditionReturn_error(ConditionMock.java:53)
     */
    @Test
    @Ignore
    public void testConditionReturn_error() {
        Mockito.when(userDao.insert(Mockito.argThat(user -> user.getUsername().equals("Bob")))).thenReturn(new User());
        Mockito.when(userDao.insert(Mockito.argThat(user -> user.getUsername().equals("David")))).thenThrow(new MockitoException("Username David not allowed to be registered"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage("Username David not allowed to be registered");

        List<User> users = Arrays.asList(new User("Bob", "bob@aa.com"), new User("David", "david@bb.com"));
        userService.batchInsert2(users);
    }

    @Test
    public void testConditionReturn() {
        Mockito.when(userDao.insert(Mockito.any())).thenAnswer(invocationOnMock -> {
            User user = invocationOnMock.getArgument(0);
            switch (user.getUsername()) {
                case "Bob":
                    user.setId(999L);
                    return user;
                case "David":
                    throw new MockitoException("Username David not allowed to be registered");
                default:
                    return null;
            }
        });
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage("Username David not allowed to be registered");

        List<User> users = Arrays.asList(new User("Bob", "bob@aa.com"), new User("David", "david@bb.com"));
        userService.batchInsert2(users);
        Assert.assertEquals(new Long(999), users.get(0).getId());
    }

}

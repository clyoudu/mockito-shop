package github.clyoudu.match;

import github.clyoudu.controller.UserController;
import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.UserService;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 15:28
 * @desc SimpleArgumentMatcher
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleArgumentMatcher {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    /**
     * anyLong() can not match null when using mockito 2.x(witch work in mockito 1.x), use nullable(Long.class instead).
     * delete @Ignore annotation for test
     */
    @Test
    @Ignore
    public void testSelectById () {
        Mockito.when(userService.selectById(Mockito.anyLong())).thenReturn(new User(0L, "mock-username", "mock-email"));
        User user = userController.userInfo(null);
        Assert.assertArrayEquals(
                new Object[]{0L, "mock-username", "mock-email"},
                new Object[]{user.getId(), user.getUsername(), user.getEmail()}
        );
    }


    @Test
    public void testSelectById_1 () {
        Mockito.when(userService.selectById(Mockito.nullable(Long.class))).thenReturn(new User(0L, "mock-username", "mock-email"));
        User user = userController.userInfo(null);
        Assert.assertArrayEquals(
                new Object[]{0L, "mock-username", "mock-email"},
                new Object[]{user.getId(), user.getUsername(), user.getEmail()}
        );
    }

}

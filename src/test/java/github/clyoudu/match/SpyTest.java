package github.clyoudu.match;

import github.clyoudu.controller.UserController;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.UserService;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author leichen
 * @date 2020/1/13 9:25 上午
 */
@RunWith(MockitoJUnitRunner.class)
public class SpyTest {

    @InjectMocks
    private UserController userController;

    @Spy
    private UserServiceImpl userService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    /**
     * NPE when use
     *      @Mock
     *      private UserServiceImpl userService;
     * because getUsername() returns null
     */
    @Test
    public void testSpy () {
        Mockito.doThrow(new MockitoException("test @Spy")).when(userService).insert(Mockito.any(User.class));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage("test @Spy");
        User user = userController.register("aa@b.com");
        Assert.assertEquals("aa", user.getUsername());
    }

}

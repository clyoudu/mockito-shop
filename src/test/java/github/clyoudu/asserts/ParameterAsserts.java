package github.clyoudu.asserts;

import github.clyoudu.controller.UserController;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author leichen
 * @date 2020/1/15 1:04 下午
 */
@RunWith(MockitoJUnitRunner.class)
public class ParameterAsserts {

    @InjectMocks
    private UserController userController;

    @Spy
    private UserServiceImpl userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    public void testParameter () {
        Mockito.doReturn(new User()).when(userService).insert(Mockito.any(User.class));
        userController.register("aaa@bb.com");
        Mockito.verify(userService).insert(userArgumentCaptor.capture());
        Assert.assertEquals("aaa", userArgumentCaptor.getValue().getUsername());
    }

    @Test
    public void testParameter1 () {
        Mockito.doReturn(new User()).when(userService).insert(Mockito.any(User.class));
        userController.register("aaa@bb.com");
        Mockito.verify(userService, Mockito.times(1))
                .insert(Mockito.argThat((ArgumentMatcher<User>) user -> "aaa".equals(user.getUsername())));
    }

}

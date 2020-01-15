package github.clyoudu.match;

import github.clyoudu.controller.UserController;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.UserService;
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
 * @time 17:59
 * @desc ReturnVoidMock
 */
@RunWith(MockitoJUnitRunner.class)
public class ReturnVoidMock {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testVoidMock () {
        Mockito.doNothing().when(userService).delete(Mockito.anyLong());
        userController.close(new User(){{setId(1L);}});
    }

    @Test
    public void testVoidMock_1 () {
        Mockito.doThrow(new MockitoException("test void throw")).when(userService).delete(Mockito.nullable(Long.class));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage(Is.is("test void throw"));
        userController.close(new User());
    }

}

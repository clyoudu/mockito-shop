package github.clyoudu.asserts;

import github.clyoudu.controller.UserController;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.UserService;
import org.hamcrest.core.Is;
import org.junit.Assert;
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
 * @author leichen
 * @date 2020/1/15 11:49 上午
 */
@RunWith(MockitoJUnitRunner.class)
public class ExceptionAsserts {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testException () {
        try{
            Mockito.when(userService.insert(Mockito.any(User.class))).thenThrow(new MockitoException("test exception"));
            userController.register(new User());
        } catch (Exception e) {
            Assert.assertTrue(e instanceof MockitoException);
            Assert.assertEquals("test exception", e.getMessage());
        }
    }

    @Test
    public void testException_1 () {
        try{
            Mockito.when(userService.insert(Mockito.any(User.class))).thenReturn(new User());
            userController.register(new User());
        } catch (Exception e) {
            Assert.assertTrue(e instanceof MockitoException);
            Assert.assertEquals("test exception", e.getMessage());
        }
    }

    @Test
    public void testException1 () {
        try{
            Mockito.when(userService.insert(Mockito.any(User.class))).thenThrow(new MockitoException("test exception"));
            userController.register(new User());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof MockitoException);
            Assert.assertEquals("test exception", e.getMessage());
        }
    }

    @Test(expected = MockitoException.class)
    public void testException2 () {
        Mockito.when(userService.insert(Mockito.any(User.class))).thenThrow(new MockitoException("test exception"));
        userController.register(new User());
    }

    @Test
    public void testException3 () {
        Mockito.when(userService.insert(Mockito.any(User.class))).thenThrow(new MockitoException("test exception"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage("test exception");
        userController.register(new User());
    }

    /**
     * Error
     */
    @Test
    public void testException4 () {
        Mockito.when(userService.insert(Mockito.any(User.class))).thenThrow(new MockitoException("test exception extra"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage(Is.is("test exception"));
        userController.register(new User());
    }

}

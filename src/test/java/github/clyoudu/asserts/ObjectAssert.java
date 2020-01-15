package github.clyoudu.asserts;

import github.clyoudu.controller.UserController;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.UserService;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author leichen
 * @date 2020/1/14 2:18 下午
 */
@RunWith(MockitoJUnitRunner.class)
public class ObjectAssert {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testSelectById_1 () {
        Mockito.when(userService.selectById(Mockito.nullable(Long.class))).thenReturn(new User(0L, "mock-username", "mock-email"));
        User user = userController.userInfo(null);
        Assert.assertEquals(new Long(0), user.getId());
        Assert.assertEquals("mock-username", user.getUsername());
        Assert.assertEquals("mock-email", user.getEmail());
    }

    @Test
    public void testSelectById_2 () {
        Mockito.when(userService.selectById(Mockito.nullable(Long.class))).thenReturn(new User(0L, "mock-username", "mock-email"));
        User user = userController.userInfo(null);
        Assert.assertArrayEquals(
                new Object[]{0L, "mock-username", "mock-email"},
                new Object[]{user.getId(), user.getUsername(), user.getEmail()}
        );
    }

    @Test
    public void testSelectById_3 () {
        Mockito.when(userService.selectById(Mockito.nullable(Long.class))).thenReturn(new User(0L, "mock-username", "mock-email"));
        User user = userController.userInfo(null);
        Assert.assertThat(user, new UserMatcher(new User(0L, "mock-username", "mock-email")));
    }

    private class UserMatcher extends BaseMatcher<User> {

        private User expectedUser;

        public UserMatcher(User expectedUser) {
            this.expectedUser = expectedUser;
        }

        @Override
        public boolean matches(Object o) {
            User actualUser = (User) o;
            return expectedUser.getId().equals(actualUser.getId()) &&
                    expectedUser.getUsername().equals(actualUser.getUsername()) &&
                    expectedUser.getEmail().equals(actualUser.getEmail());
        }

        @Override
        public void describeTo(Description description) {
            description.appendValue("Actual User is not match expected User");
        }
    }

}

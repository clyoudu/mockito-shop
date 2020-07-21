package github.clyoudu.others;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @author leichen
 * @date 2020/7/21 1:37 下午
 * @since
 */
@RunWith(Parameterized.class)
public class RunWithOtherRunnersTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private String email;

    public RunWithOtherRunnersTest(String email) {
        this.email = email;
    }

    @Parameterized.Parameters
    public static String[] data() {
        return new String[]{
                "a@a.com", "b@b.com", "c@c.com"
        };
    }

    @Test
    public void testInsertEmailValid() {
        Mockito.when(userDao.insert(Mockito.any())).thenReturn(new User());
        userService.insert(email);
    }
}

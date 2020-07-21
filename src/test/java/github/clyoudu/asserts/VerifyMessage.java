package github.clyoudu.asserts;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author leichen
 * @date 2020/7/21 1:47 下午
 * @since
 */
@RunWith(MockitoJUnitRunner.class)
public class VerifyMessage {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Test
    public void testUpsert() {
        userService.upsert(new User(1L, "a", "b@q.com"));
        InOrder order = Mockito.inOrder(userDao);

        Mockito.verify(userDao, Mockito.times(2).description("该方法应该调用两次")).insert(Mockito.any());
    }
}
/**
 * org.mockito.exceptions.base.MockitoAssertionError: 该方法应该调用两次
 *
 * userDao.insert(<any>);
 * Wanted 2 times:
 * -> at github.clyoudu.asserts.VerifyMessage.testUpsert(VerifyMessage.java:33)
 * But was 1 time:
 * -> at github.clyoudu.service.impl.UserServiceImpl.insert(UserServiceImpl.java:40)
 */

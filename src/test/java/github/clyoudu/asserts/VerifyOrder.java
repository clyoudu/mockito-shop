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
 * @date 2020/7/21 1:07 下午
 * @since
 */
@RunWith(MockitoJUnitRunner.class)
public class VerifyOrder {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Test
    public void testUpsert() {
        userService.upsert(new User(1L, "a", "b@q.com"));
        InOrder order = Mockito.inOrder(userDao);

        order.verify(userDao).selectById(Mockito.eq(1L));
        order.verify(userDao).insert(Mockito.any());
    }

}

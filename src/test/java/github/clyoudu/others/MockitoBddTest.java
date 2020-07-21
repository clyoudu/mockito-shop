package github.clyoudu.others;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

/**
 * @author leichen
 * @date 2020/7/21 1:26 下午
 * @since
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoBddTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Test
    public void testWithoutBdd() {
        Mockito.when(userDao.insert(Mockito.any(User.class))).thenReturn(new User());
        userService.batchInsert2(Collections.singletonList(new User()));
        Mockito.verify(userDao, Mockito.times(1)).insert(Mockito.any(User.class));
    }

    @Test
    public void testWithBdd() {
        // given
        BDDMockito.given(userDao.insert(BDDMockito.any(User.class))).willReturn(new User());

        // when
        userService.batchInsert2(Collections.singletonList(new User()));

        // then
        BDDMockito.then(userDao).should(BDDMockito.times(1)).insert(BDDMockito.any(User.class));
    }

}

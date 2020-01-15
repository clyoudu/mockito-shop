package github.clyoudu.asserts;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author leichen
 * @date 2020/1/15 2:02 下午
 */
@RunWith(MockitoJUnitRunner.class)
public class VerifyAsserts {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Test
    public void testVerifyTimes () {
        userService.batchInsert2(new ArrayList<>());
        Mockito.verify(userDao, Mockito.never()).insert(Mockito.any(User.class));
    }

    @Test
    public void testVerifyTimes1 () {
        Mockito.when(userDao.insert(Mockito.any(User.class))).thenReturn(new User());
        userService.batchInsert2(Collections.singletonList(new User()));
        Mockito.verify(userDao, Mockito.times(1)).insert(Mockito.any(User.class));
        Mockito.verify(userDao, Mockito.atMost(1)).insert(Mockito.any(User.class));
        Mockito.verify(userDao, Mockito.atLeast(1)).insert(Mockito.any(User.class));
    }

}

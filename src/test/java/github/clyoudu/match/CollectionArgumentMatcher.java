package github.clyoudu.match;

import github.clyoudu.dao.UserDao;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 16:11
 * @desc CollectionArgumentMatcher
 */
@RunWith(MockitoJUnitRunner.class)
public class CollectionArgumentMatcher {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testAnyList () {
        Mockito.when(userDao.batchInsert(Mockito.anyList())).thenThrow(new MockitoException("test anyList()"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage("test anyList()");
        userService.batchInsert(Arrays.asList(new User("Bob", "bob@aa.com"), new User("David", "david@bb.com")));

    }

    @Test
    public void testListSize () {
        Mockito.when(userDao.batchInsert(Mockito.argThat(users -> users.size() == 2))).thenThrow(new MockitoException("test match list size"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage("test match list size");
        userService.batchInsert(Arrays.asList(new User("Bob", "bob@aa.com"), new User("David", "david@bb.com")));

    }

    @Test
    public void testListType () {
        Mockito.when(userDao.batchInsert(Mockito.argThat(users -> users instanceof LinkedList))).thenThrow(new MockitoException("test match list type"));
        expectedEx.expect(MockitoException.class);
        expectedEx.expectMessage("test match list type");
        userService.batchInsert(new LinkedList<>(Arrays.asList(new User("Bob", "bob@aa.com"), new User("David", "david@bb.com"))));

    }
}

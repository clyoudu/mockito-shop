package github.clyoudu.asserts;

import github.clyoudu.dao.UserDao;
import github.clyoudu.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.stereotype.Repository;

/**
 * @author leichen
 * @date 2020/7/21 2:00 下午
 * @since
 */
@RunWith(MockitoJUnitRunner.class)
public class AssertAnnotationAndMetaData {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Test
    public void testAnnotation() {
        Assert.assertTrue(userDao.getClass().isAnnotationPresent(Repository.class));
    }

}

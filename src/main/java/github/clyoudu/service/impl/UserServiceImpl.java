package github.clyoudu.service.impl;

import github.clyoudu.dao.UserDao;
import github.clyoudu.exceptions.IllegalEmailException;
import github.clyoudu.exceptions.IllegalUsernameException;
import github.clyoudu.exceptions.NullUserException;
import github.clyoudu.exceptions.UpdateUserException;
import github.clyoudu.model.pojo.User;
import github.clyoudu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 13:59
 * @desc UserServiceImpl
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User insert(User user) {
        if (user == null) {
            throw new NullUserException("用户不能为空");
        }
        checkUsername(user.getUsername());
        checkEmail(user.getEmail());
        return userDao.insert(user);
    }

    @Override
    public int batchInsert(List<User> users) {
        if (users == null || users.isEmpty()) {
            throw new NullUserException("用户列表不能为空");
        }
        users.forEach(user -> {
            checkUsername(user.getUsername());
            checkEmail(user.getEmail());
        });
        return userDao.batchInsert(users);
    }

    @Override
    public int batchInsert2(List<User> users) {
        users.forEach(user -> userDao.insert(user));
        return users.size();
    }

    @Override
    public User insert(String username, String email) {
        return insert(new User(username, email));
    }

    @Override
    public User insert(String email) {
        checkEmail(email);
        User user = new User(getUsername(email), email);
        return insert(user);
    }

    @Override
    public User insert(List<User> users) {
        return null;
    }


    @Override
    public synchronized User upsert(User user) {
        if (user.getId() != null) {
            return update(user);
        }
        return insert(user);
    }

    @Override
    public void delete(Long id) {
        if (!userDao.delete(id)) {
            throw new UpdateUserException("删除的用户不存在");
        }
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new NullUserException("用户不能为空");
        }
        checkId(user.getId());
        checkUsername(user.getUsername());
        checkEmail(user.getEmail());
        return userDao.update(user);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public User selectById(Long id) {
        checkId(id);
        return userDao.selectById(id);
    }

    private String getUsername(String email) {
        return email.substring(0, email.indexOf("@"));
    }

    private void checkId (Long id) {
        if (id == null) {
            throw new NullUserException("用户ID不能为空");
        }
    }

    private void checkUsername (String username) {
        if (StringUtils.isEmpty(username)) {
            throw new NullUserException("用户名不能为空");
        }

        if (username.length() > 16) {
            throw new IllegalUsernameException("用户名不能超过16个字符");
        }

        if (!username.matches("([a-zA-Z])+[a-zA-Z0-9_]*")) {
            throw new IllegalUsernameException("用户名只能以字母开头且只能包含字母、数字、下划线");
        }
    }

    private void checkEmail (String email) {
        if (StringUtils.isEmpty(email)) {
            throw new NullUserException("用户邮箱不能为空");
        }

        if (!email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {
            throw new IllegalEmailException("用户邮箱格式有误");
        }
    }
}

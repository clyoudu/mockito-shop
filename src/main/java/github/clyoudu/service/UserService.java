package github.clyoudu.service;

import github.clyoudu.model.pojo.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 13:58
 * @desc UserService
 */
public interface UserService {

    User insert(User user);
    int batchInsert(List<User> users);
    int batchInsert2(List<User> users);
    User insert(String username, String email);
    User insert(String email);
    User insert(List<User> users);
    User upsert(User user);
    void delete (Long id);
    User update (User user);
    List<User> selectAll ();
    User selectById (Long id);
}

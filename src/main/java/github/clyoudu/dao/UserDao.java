package github.clyoudu.dao;

import github.clyoudu.model.pojo.User;
import github.clyoudu.util.IdGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 13:38
 * @desc UserDao
 */
@Repository
public class UserDao {

    private static final CopyOnWriteArrayList<User> USERS = new CopyOnWriteArrayList<>();

    public User insert(User user) {
        user.setId(IdGenerator.getId());
        USERS.add(user);
        return user;
    }

    public int batchInsert (List<User> users) {
        users.forEach(user -> user.setId(IdGenerator.getId()));
        USERS.addAll(users);
        return users.size();
    }

    public boolean delete (Long id) {
        return USERS.removeIf(user -> Objects.equals(id, user.getId()));
    }

    public synchronized User update (User user) {
        USERS.stream().filter(u -> u.getId().equals(user.getId())).forEach(u -> u = user);
        return user;
    }

    public List<User> selectAll () {
        return new ArrayList<>(USERS);
    }

    public User selectById (Long id) {
        return USERS.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public final int count(){
        return USERS.size();
    }

    public List<User> search(String keyword) {
        return USERS.stream().filter(user -> match(user, keyword)).collect(Collectors.toList());
    }

    private boolean match(User user, String keyword) {
        if(user.getUsername().contains(keyword) || user.getEmail().contains(keyword)) {
            return true;
        }

        return false;
    }

    protected boolean match2(User user, String keyword) {
        if(user.getUsername().contains(keyword) || user.getEmail().contains(keyword)) {
            return true;
        }

        return false;
    }

    public List<User> search2(String keyword) {
        return USERS.stream().filter(user -> match2(user, keyword)).collect(Collectors.toList());
    }
}

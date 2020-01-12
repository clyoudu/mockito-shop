package github.clyoudu.controller;

import github.clyoudu.model.pojo.User;
import github.clyoudu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 14:15
 * @desc UserController
 */
@RestController("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public User register (@RequestBody User user) {
        return userService.insert(user);
    }

    @PostMapping("register/email")
    public User register (@RequestBody String email) {
        return userService.insert(email);
    }

    @DeleteMapping("close")
    public void close (@RequestBody User user) {
        userService.delete(user.getId());
    }

    @PutMapping("update")
    public User update (@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("{id}")
    public User userInfo (@PathVariable Long id) {
        return userService.selectById(id);
    }

    @GetMapping("all")
    public List<User> allUser () {
        return userService.selectAll();
    }

}

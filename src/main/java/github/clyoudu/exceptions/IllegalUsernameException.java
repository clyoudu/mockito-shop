package github.clyoudu.exceptions;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 14:29
 * @desc IllegalUsernameException
 */
public class IllegalUsernameException extends IllegalArgumentException {
    public IllegalUsernameException (String message) {
        super(message);
    }
}

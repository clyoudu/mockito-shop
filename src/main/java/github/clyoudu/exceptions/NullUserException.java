package github.clyoudu.exceptions;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 14:04
 * @desc NullUserException
 */
public class NullUserException extends RuntimeException {
    public NullUserException (String message) {
        super(message);
    }
}

package github.clyoudu.exceptions;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 14:34
 * @desc IllegalEmailException
 */
public class IllegalEmailException extends IllegalArgumentException {
    public IllegalEmailException (String email) {
        super(email);
    }
}

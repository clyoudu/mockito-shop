package github.clyoudu.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 13:43
 * @desc IdGenerator
 */
public class IdGenerator {

    private final static AtomicLong ID = new AtomicLong(1);

    private IdGenerator () {}

    public static Long getId () {
        return ID.getAndAdd(1L);
    }

    public static boolean generated (Long id) {
        return ID.get() > id;
    }
}

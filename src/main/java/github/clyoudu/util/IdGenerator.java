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

    public static Long getId () {
        return ID.getAndAdd(1L);
    }

    public final boolean generated (Long id) {
        return ID.get() > id;
    }

    private Long getCurrentId1() {
        return ID.get();
    }

    protected Long getCurrentId2() {
        return ID.get();
    }

    public Long getCurrentIdViaPrivate() {
        return getCurrentId1();
    }

    public Long getCurrentIdViaProtected() {
        return getCurrentId2();
    }
}

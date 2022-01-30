package directmemory;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/10/18 - 0:14
 **/
public class MaxDirectMemorySizeTest {

    private static final long _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}

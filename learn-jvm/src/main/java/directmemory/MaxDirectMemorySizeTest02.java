package directmemory;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/10/18 - 0:23
 **/
public class MaxDirectMemorySizeTest02 {
    private static final int BUFFER = 1024 * 1024 * 20;

    public static void main(String[] args) {
        ArrayList<ByteBuffer> list = new ArrayList<>();

        int count = 0;
        try {
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER);
                list.add(buffer);
                count++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            System.out.println(count);
        }
    }
}

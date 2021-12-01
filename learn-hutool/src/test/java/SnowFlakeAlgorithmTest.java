import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import org.junit.Test;

import java.util.Map;


/**
 * 雪花算法业务逻辑
 *
 * @author huangjinhai
 * @date 2021-11-29
 */
public class SnowFlakeAlgorithmTest {

    @Test
    public void testId() throws InterruptedException {
//        int machine = getMachinePiece();
//        int process = ObjectId.getProcessPiece();
//        System.out.println(machine + "--" + process);
//        System.out.println((machine & 31) + "--" + (process & 31));
        Snowflake sf = IdUtil.getSnowflake();
        // IdUtil.createSnowflake(machine&31, process&31);
        Map<Long, String> ids = MapUtil.newConcurrentHashMap();
        int counter = 10;
        int threadNum = 2;
        TimeInterval timer = DateUtil.timer();

        ConcurrencyTester tester = ThreadUtil.concurrencyTest(threadNum, () -> {
            // 测试的逻辑内容
            for (int i = 0; i < counter; i++) {
                ids.put(sf.nextId(), "");
                // String temp = IdUtil.randomUUID();
                // ids.add(IdUtil.randomUUID());
            }
        });

        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());

        // 获取总的执行时间，单位毫秒
        System.out.println(timer.interval() + " ms");
        System.out.println("SIZE：" + ids.size());
        System.out.println(ids);
        System.out.println(Long.MAX_VALUE);
    }

}

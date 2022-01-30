import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import org.junit.Test;

import java.util.Date;

/**
 * 日期时间方法测试
 *
 * @author huangjinhai
 * @date 2021-11-29
 */
public class DateTimeTest {


    @Test
    public void testDateTime() throws InterruptedException {
        /**
         * 当前时间
         */
        Date now = DateUtil.date();
        System.out.println(now);
        String nowString = DateUtil.now();
        System.out.println(nowString);
        /**
         * 当前日期
         */
        String nowDate = DateUtil.today();
        System.out.println(nowDate);

        /**
         * 字符串转日期 yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm yyyy-MM-dd
         * HH:mm:ss.SSS
         *
         * DatePattern
         */
        String dateStr = "2021-2-12";
        System.out.println(DateUtil.parse(dateStr));
        System.out.println(DateUtil.parse(dateStr, "yyyy-MM-dd"));

        /**
         * 获取Date对象的某个部分
         */
        System.out.println(DateUtil.year(now));
        System.out.println(DateUtil.month(now) + 1);
        System.out.println(DateUtil.dayOfMonth(now));
        System.out.println(DateUtil.hour(now, true));
        System.out.println(DateUtil.minute(now));
        System.out.println(DateUtil.second(now));

        /**
         * 开始和结束时间
         */
        System.out.println(DateUtil.beginOfDay(now));
        System.out.println(DateUtil.endOfDay(now));

        /**
         * 时间偏移
         */
        System.out.println(DateUtil.offsetHour(now, 3));
        System.out.println(DateUtil.yesterday());
    }


    @Test
    public void testDateTime2() throws InterruptedException {
        /**
         * 当前时间
         */
        Date now = DateUtil.date();
        System.out.println(now);
        String nowString = DateUtil.now();
        System.out.println(nowString);
        /**
         * 时间差
         */
        Date from = DateUtil.date();
        Date end = DateUtil.offsetMillisecond(from, 988989898);
        System.out.println(DateUtil.between(from, end, DateUnit.HOUR));
        System.out.println(DateUtil.between(from, end, DateUnit.DAY));
        /**
         * 格式化时间差，默认精确到毫秒差距
         */
        System.out.println("差：" + DateUtil.formatBetween(from, end));

        /**
         * 计时器
         */
        TimeInterval timer = DateUtil.timer();
        Thread.sleep(2000L);
        System.out.println(timer.interval() + " ms");
        timer.intervalRestart();
        System.out.println(timer.intervalMinute() + " min");

        /**
         * 格式化字符串 DatePattern
         */

        /**
         * 农历
         */
        ChineseDate chineseDate = new ChineseDate(DateUtil.parseDate("2020-08-28"));
        String cyclicalYmd = chineseDate.getCyclicalYMD();
        System.out.println(cyclicalYmd);

        /**
         * 对JDK 8 LocalDateTime和LocalDate的支持
         */

    }







}

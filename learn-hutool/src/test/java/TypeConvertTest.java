import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * 类型转换测试
 *
 * @author huangjinhai
 * @date 2021-11-29
 */
public class TypeConvertTest {


    @Test
    public void testConvert() {
        /**
         * 字符串编码转换
         */
        String a = "Hello 中国";
        String hex = Convert.toHex(a, CharsetUtil.CHARSET_UTF_8);
        String raw = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(raw);
        /**
         * 时间转换，毫秒转成XX小时等
         */
        long b = 1000 * 60 * 60 * 24;
        long mins = Convert.convertTime(b, TimeUnit.MILLISECONDS, TimeUnit.HOURS);
        System.out.println(mins);
        /**
         * 金额大小写转换
         */
        double c = 288.66;
        System.out.println(Convert.digitToChinese(c));

    }


}

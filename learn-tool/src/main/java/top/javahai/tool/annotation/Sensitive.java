package top.javahai.tool.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import top.javahai.tool.jackson.SensitiveSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2022/7/19 - 0:03
 **/
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerializer.class)
public @interface Sensitive {
    /**
     * 脱敏的类型,默认手机号
     * @return
     */
    Type value();

    /**
     * CUSTOM_HIDE/CUSTOM_OVERLAY 时生效
     * 开始位置（包含）
     * @return
     */
    int startInclude() default 0;

    /**
     * CUSTOM_HIDE/CUSTOM_OVERLAY 时生效
     * 结束位置（不包含）
     * @return
     */
    int endExclude() default 0;


    /**
     * CUSTOM_OVERLAY 时生效,*重复的次数
     * @return
     */
    int overlayRepeat() default 4;


    /**
     * Enumeration used with {@link Sensitive}
     */
    public enum Type {
        /**
         * 手机号
         */
        MOBILE,
        /**
         * 中文名
         */
        CHINESE_NAME,
        /**
         * 身份证号
         */
        ID_CARD,
        /**
         * 座机号
         */
        FIXED_PHONE,
        /**
         * 地址
         */
        ADDRESS,
        /**
         * 电子邮件
         */
        EMAIL,
        /**
         * 银行卡
         */
        BANK_CARD,
        /**
         * 自定义，有多少个字符替换成多少个*
         * e.g: startInclude=3,endExclude=7,隐藏第3个到第7个的字符
         */
        CUSTOM_HIDE,
        /**
         *保留方式隐藏
         * e.g: startInclude=3,endExclude=4 ,保留前面3个和后面的4个
         */
        CUSTOM_RETAIN_HIDE,
        /**
         * 自定义,只替换成指定个*
         */
        CUSTOM_OVERLAY,
    }

}


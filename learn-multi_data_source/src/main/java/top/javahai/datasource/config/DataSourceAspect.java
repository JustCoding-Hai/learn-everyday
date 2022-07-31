package top.javahai.datasource.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import org.aspectj.lang.reflect.MethodSignature;
import top.javahai.datasource.annotation.SpecifyDataSource;

import java.lang.reflect.Method;

/**
 * @author Ethan Huang
 * @create 2020-11-19 14:58
 */
@Aspect
@Component
@Order(value = 1)
public class DataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(top.javahai.datasource.annotation.SpecifyDataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        SpecifyDataSource ds = method.getAnnotation(SpecifyDataSource.class);
        if (ds == null) {
            DynamicDataSource.setDataSource(DataSourceTypeEnum.CHATROOM.getName());
            logger.info("set datasource is " + DataSourceTypeEnum.CHATROOM);
        } else {
            DynamicDataSource.setDataSource(ds.value().getName());
            logger.info("set datasource is " + ds.value().getName());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            logger.info("clean datasource");
        }
    }

}

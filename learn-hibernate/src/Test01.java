import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.javahai.entity.NewsEntity;

import java.util.Date;

/**
 * @author Hai
 * @date 2020/7/12 - 0:15
 */
public class Test01 {
      private SessionFactory sessionFactory;
      private Session session;
      private Transaction transaction;

      @Before
      public void init(){
        //创建配置对象
        Configuration config = new Configuration().configure();
        //创建服务注册对象
        //  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();（使用这种方法会报错，unkonw Entity 。。。。）
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        //创建会话工厂对象
        sessionFactory = config.buildSessionFactory(serviceRegistry);
        //创建会话对象
        session  = sessionFactory.openSession();
        //开启事务
        transaction = session.beginTransaction();
      }

      @Test
      public void testSaveStudents(){
        NewsEntity newsEntity = new NewsEntity("aaa","22",new Date());
        session.save(newsEntity);
      }

      @After
      public void destory(){
        //提交事务
        transaction.commit();
        //关闭session
        session.close();
        //关闭sessionFactory
        sessionFactory.close();
      }
}


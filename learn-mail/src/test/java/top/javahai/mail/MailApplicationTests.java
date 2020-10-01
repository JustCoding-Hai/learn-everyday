package top.javahai.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@SpringBootTest
class MailApplicationTests {

  @Autowired
  JavaMailSender javaMailSender;
  @Test
  void contextLoads() {
    SimpleMailMessage msg=new SimpleMailMessage();
    msg.setSubject("这是测试邮件主题");
    msg.setText("这是测试邮件内容");
    msg.setFrom("1258398543@qq.com");
    msg.setSentDate(new Date());
    msg.setTo("jinhaihuang824@aliyun.com");
    javaMailSender.send(msg);
  }
   //发送附件
  @Test
  public void test01() throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setSubject("这是测试邮件主题");
    helper.setText("这是测试邮件内容");
    helper.setFrom("1258398543@qq.com");
    helper.setSentDate(new Date());
    helper.setTo("jinhaihuang824@aliyun.com");
    //附件
    helper.addAttachment("test.png",new File("C:\\Users\\LENOVO\\Desktop\\图片\\1.png"));
   // helper.addAttachment("test.png", new File("https://img-blog.csdnimg.cn/20200623222810162.jpg"));
    javaMailSender.send(mimeMessage);
  }
  //发送带有图片的内容
  @Test
  public void test02() throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setSubject("这是测试邮件主题");
    helper.setText("这是测试邮件内容"+"<img src='https://img-blog.csdnimg.cn/20200623222810162.jpg'/>",true);
    helper.setFrom("1258398543@qq.com");
    helper.setSentDate(new Date());
    helper.setTo("jinhaihuang824@aliyun.com");
    //附件
    helper.addAttachment("test.png",new File("C:\\Users\\LENOVO\\Desktop\\图片\\1.png"));
    // helper.addAttachment("test.png", new File("https://img-blog.csdnimg.cn/20200623222810162.jpg"));
    javaMailSender.send(mimeMessage);
  }
}

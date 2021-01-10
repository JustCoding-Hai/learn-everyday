import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import org.junit.Test;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/1/10 - 22:15
 **/
public class OssTest {


    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "<accessKeyId>";
    String accessKeySecret = "<accessKeySecret>";
    String bucketName = "admin-test123";

    /**
     * 1.测试创建Bucket
     */
    @Test
    public void testCreateBucket() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建CreateBucketRequest对象。
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

        //判断Bucket是否存在
        boolean bucketExist = ossClient.doesBucketExist(bucketName);

        if (!bucketExist) {
            // 创建存储空间。
            ossClient.createBucket(createBucketRequest);
        }

        //ossClient.createBucket(createBucketRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testAccessControl(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

        ossClient.shutdown();

    }


}

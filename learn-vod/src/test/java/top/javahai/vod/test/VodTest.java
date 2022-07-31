package top.javahai.vod.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/1/19 - 16:47
 **/
public class VodTest {

    String accessKeyId="<你的accessKeyId>";
    String accessKeySecret="<你的accessKeySecret>";


    /**
     * 测试获取普通视频信息
     */
    @Test
    public void testGetPlayInfo(){
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        try {
            response = getPlayInfo(client);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }


    /**
     *测试获取加密视频的信息
     */
    @Test
    public void testGetVideoPlayAuth() {

        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = getVideoPlayAuth(client);
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }




    /**
     * 获取播放凭证函数
     * @param client
     * @return
     * @throws Exception
     */
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("2370e64d2f9546ebb9d27a72016cb1bf");
        return client.getAcsResponse(request);
    }

    /**
     * 获取播放地址函数
     * @param client
     * @return
     * @throws Exception
     */
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("2370e64d2f9546ebb9d27a72016cb1bf");
        request.setAuthTimeout(3000L);
        //request.setResultType("Multiple"); //如果视频id是加密视频的id，则需要设置此参数。但只能获取文件无法解密播放
        return client.getAcsResponse(request);
    }

    /**
     * 初始化方法
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     */
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        // 点播服务接入区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    @Test
    public void testOptional() {
        User user=new User();
        System.out.println(user);
        user = Optional.ofNullable(user).orElse(createUser());
        User user1=null;
        user1 = Optional.ofNullable(user).orElseGet(() -> createUser());
        System.out.println(user);
        System.out.println(user1);
    }

    public User createUser(){
        User user = new User();
        user.setName("zhangsan");
        System.out.println("createUser执行");
        return user;
    }

}

class User{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


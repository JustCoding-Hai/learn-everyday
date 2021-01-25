package top.javahai.learn.jwt;

import io.jsonwebtoken.Claims;
import org.junit.Test;
import top.javahai.learn.jwt.entity.Member;
import top.javahai.learn.jwt.utils.JwtUtils;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: Jwt测试
 * @create 2021/1/25 - 17:43
 **/
public class JwtTest {

    @Test
    public void testGetToken(){
        Member member = new Member();
        member.setId("1");
        member.setAvatar("123");
        member.setNickname("ethan");
        String s = JwtUtils.generateJwt(member);
        System.out.println(s);
    }

    @Test
    public void testParseToken(){
        Member member = new Member();
        member.setId("1");
        member.setAvatar("123");
        member.setNickname("ethan");
        String s = JwtUtils.generateJwt(member);
        Claims claims = JwtUtils.checkJwt(s);
        String id = claims.get("id", String.class);
        String nickname = claims.get("nickname", String.class);
        String avatar = claims.get("avatar", String.class);
        System.out.println(id);
        System.out.println(nickname);
        System.out.println(avatar);
    }
}

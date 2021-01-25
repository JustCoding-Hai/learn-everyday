package top.javahai.learn.jwt.utils;

import io.jsonwebtoken.*;
import top.javahai.learn.jwt.entity.Member;

import java.awt.*;
import java.awt.dnd.DropTarget;
import java.util.Date;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/1/25 - 17:25
 **/
public class JwtUtils {

    private static final String APP_SECRET="79e7c69681b8270162386e6daa53d1dc";

    private static final long EXPIRE_TIME=1000*60*30;

    /**
     * 生成Jwt令牌
     * @param member
     * @return
     */
    public static String generateJwt(Member member){
        //创建Builder对象
        JwtBuilder builder = Jwts.builder();

        //第一部分：Jwt头 header
        builder.setHeaderParam("alg","HS256");
        builder.setHeaderParam("typ","JWT");

        //第二部分：有效载荷 payload
        //id为jwt的唯一身份标识，主要用来作为一次性token，从而回避重放攻击
        builder.setId("1");
        //令牌的主题
        builder.setSubject("test");
        //令牌的签发时间
        builder.setIssuedAt(new Date());
        //令牌的过期时间
        builder.setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME));

        //设置第二部分的私用字段
        builder.claim("id", member.getId());
        builder.claim("nickname",member.getNickname());
        builder.claim("avatar",member.getAvatar());

        //第三部分：签名哈希 VERIFY SIGNATURE
        builder.signWith(SignatureAlgorithm.HS256,APP_SECRET);

        //将三部分连接起来
        String token = builder.compact();

        return token;
    }

    /**
     * 校验和解析Token
     * @param jwtToken
     * @return
     */
    public static Claims checkJwt(String jwtToken){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        JwsHeader header = claimsJws.getHeader();
        String signature = claimsJws.getSignature();
        System.out.println(header.getKeyId());
        System.out.println(signature);
        Claims claims = claimsJws.getBody();
        return claims;
    }
}

package zdl.blogsystem.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import zdl.blogsystem.common.excepiton.BlogException;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {
    public static final String SECRETSTRING = "ZVWixSDInZzd5qMkUDfaWCOjs84Gh5jXN886tQhYhiM=";
    public static final Key KEY = Keys.hmacShaKeyFor(SECRETSTRING.getBytes());
    //token有效时间
    public static final long EXPIRATION = 24*60*60*1000;

    //生成token
    public static String createToken(Map<String,Object>claim){
        String token = Jwts.builder().setClaims(claim) //⾃定义内容(载荷)
                .setIssuedAt(new Date())// 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) //设置过期时间
                .signWith(KEY) //签名算法
                .compact();

        return token;
    }

    //校验token
    public static Claims parseToken(String token){
        if(!StringUtils.hasLength(token)){
            return  null;
        }

        //创建解析器, 设置签名密钥
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(KEY).build();

        Claims claims = null;

        try {
            claims = jwtParser.parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            log.error("token过期",e);
            throw e;
        }
        catch (SignatureException e) {
            log.error("token签名错误",e);
            throw e;
        }catch (Exception e){
            log.error("token解析失败",e);
            throw e;
        }
        return claims;
    }

    //token 中获取id
    public static Integer getIdByToken(String token){
        Claims claims = parseToken(token);
        if(claims!=null){
            return (Integer) claims.get("id");
        }
        return null;
    }
}

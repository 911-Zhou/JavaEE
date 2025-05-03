package zdl.blogsystem.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {
    public static final String secretString = "ZVWixSDInZzd5qMkUDfaWCOjs84Gh5jXN886tQhYhiM=";
    public static final Key KEY = Keys.hmacShaKeyFor(secretString.getBytes());
    @Test
    //生成token
    public void CreateJwt(){
        Map<String,Object>claim  = new HashMap<>();
        claim.put("username","周东林");
        claim.put("userid","3618");
        Jwts.builder().setClaims(claim).signWith(KEY).compact();
    }

    //生成KEY
    @Test
    public void AutoGenKey() {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.valueOf("HS256"));
        String secretString = Encoders.BASE64.encode(secretKey.getEncoded());
        System.out.println(secretString);
    }
}
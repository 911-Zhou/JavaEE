package org.zdl.anli.utils.MyCaptcha;
import java.util.Random;

//1. 后端生成验证码（4位数字符验证码）
//可以通过生成一个随机的验证码字符串，包括大写字母、小写字母和数字。
public class CaptchaGenerator {
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CAPTCHA_LENGTH = 4;

    public static String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder(CAPTCHA_LENGTH);

        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            captcha.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }

        return captcha.toString();
    }
}


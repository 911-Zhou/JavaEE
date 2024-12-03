package org.zdl.anli.utils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {
    @Data
    public static class Session{
        private String key;
        private String date;
    }

    private Integer width;
    private Integer height;
    private Session session;

}

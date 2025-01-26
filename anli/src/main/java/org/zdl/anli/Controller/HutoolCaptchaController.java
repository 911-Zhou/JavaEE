package org.zdl.anli.Controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zdl.anli.utils.CaptchaProperties;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/captcha")
@Slf4j
public class HutoolCaptchaController {
    //日志类
//    private static Logger logger = LoggerFactory.getLogger(HutoolCaptchaController.class);

    @Autowired
    private CaptchaProperties captchaProperties;

    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), 4, 4);

        System.out.println("生成验证码:" + captcha.getCode());
        log.info("logger日志生成：" + "生成验证码:" + captcha.getCode());


        //将验证码存到session中
        session.setAttribute(captchaProperties.getSession().getKey(),captcha.getCode());
        System.out.println(new Date().getTime());
        session.setAttribute(captchaProperties.getSession().getDate(),new Date().getTime());

        response.setContentType("image/jpeg");

        captcha.write(response.getOutputStream());
    }

    @RequestMapping("/check")
    public boolean checkCaptcha(String captcha,HttpSession session){
        //请求验证
        System.out.println("验证请求：" + captcha);

        String key = (String) session.getAttribute(captchaProperties.getSession().getKey());
        long time = (long) session.getAttribute(captchaProperties.getSession().getDate());
        //验证码过期
        System.out.println(System.currentTimeMillis());
        System.out.println(time);
        if(System.currentTimeMillis() - time > 60*1000){
            return false;
        }

        System.out.println(key.equals(captcha));
        return key.equals(captcha);
    }
}

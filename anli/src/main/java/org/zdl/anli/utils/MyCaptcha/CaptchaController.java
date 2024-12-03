package org.zdl.anli.utils.MyCaptcha;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;

//3. 将验证码图片返回给前端
//在MVC项目的控制器中，可以将生成的验证码图片返回给前端。比如，使用Spring MVC作为框架时，你可以这样做：
@Controller
public class CaptchaController {

    @GetMapping("/captcha")
    @ResponseBody
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        // 生成验证码
        String captcha = CaptchaGenerator.generateCaptcha();

        // 保存验证码到Session，用于后续验证
        session.setAttribute("captcha", captcha);

        // 生成验证码图片
        byte[] imageBytes = CaptchaImageGenerator.generateCaptchaImage(captcha);

        // 设置响应头，告诉浏览器返回的是图片数据
        response.setContentType("image/jpeg");
        response.getOutputStream().write(imageBytes);
    }
}

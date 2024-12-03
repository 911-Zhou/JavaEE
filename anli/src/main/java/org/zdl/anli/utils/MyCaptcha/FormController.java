package org.zdl.anli.utils.MyCaptcha;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class FormController {

    @RequestMapping("/submit")
    public String submitForm(@RequestParam("userCaptcha") String userCaptcha, HttpServletRequest request) {
        //提交请求触发
        System.out.println("提交请求触发 提交数据为：" + userCaptcha);

        // 从Session获取生成的验证码
        String generatedCaptcha = (String) request.getSession().getAttribute("captcha");

        // 验证用户输入的验证码
        if (generatedCaptcha != null && generatedCaptcha.equalsIgnoreCase(userCaptcha)) {
            // 验证通过
            return "formSuccess";
        } else {
            // 验证失败
            return "formError";
        }
    }
}


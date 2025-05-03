package zdl.springaop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class testController {
    @RequestMapping("t1")
    @ResponseBody
    public String t1(){
        return "T1";
    }

    @RequestMapping("t2")
    @ResponseBody
    public String t2(){
        return "T2";
    }
    @RequestMapping("t3")
    @ResponseBody
    public String t3(){
        return "T3";
    }
    @RequestMapping("t4")
    @ResponseBody
    public String t4(){
        return "T4";
    }
}

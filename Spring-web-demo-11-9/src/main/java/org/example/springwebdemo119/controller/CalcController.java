package org.example.springwebdemo119.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/calc")
@Controller
public class CalcController {
    @ResponseBody
    @RequestMapping(value="/sum")
    public String sum(Integer num1,Integer num2){
        return String.valueOf(num1+num2);
    }

}

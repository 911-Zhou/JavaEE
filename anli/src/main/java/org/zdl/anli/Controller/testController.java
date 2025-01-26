package org.zdl.anli.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class testController {
    private static Logger logger = LoggerFactory.getLogger(testController.class);

    @RequestMapping("/print")
    public String print(){
        logger.error("error级别日志");
        logger.warn("warn级别日志");
        logger.info("info级别日志");
        logger.debug("debug级别日志");
        logger.trace("trace级别日志");
        return "日志打印";
    }
}

package com.byhuang.springbootinitidea;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/11/2 21:33
 * @description TODO
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello springboot init...";
    }
}

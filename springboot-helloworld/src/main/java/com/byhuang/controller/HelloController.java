package com.byhuang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/11/2 20:59
 * @description TODO
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello springboot...";
    }
}

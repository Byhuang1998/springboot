package com.byhuang.springbootinitidea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/11/2 21:33
 * @description TODO
 */
@RestController
public class HelloController {

    @Value("${name}")
    private String name;

    @Value("${person.age}")
    private int age;

    @Value("${address[0]")
    private String address1;

    @Value("${str1}")
    private String str1;

    @Value("${str2}")
    private String str2;

    @Autowired
    private Environment env;

    @Autowired
    private Person person;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("========直接通过注解来来一个个获取yaml文件中的数据========");
        System.out.println(name);
        System.out.println(age);
        System.out.println(address1);
        System.out.println(str1);
        System.out.println(str2);

        System.out.println("========可以通过Environment变量来直接获取yaml文件中的数据========");
        System.out.println(env.getProperty("person.name"));

        System.out.println("========可以通过ConfigurationProperties注解获取yaml文件中的数据========");
        System.out.println(person);
        String[] address = person.getAddress();
        Arrays.stream(address).forEach(System.out::print);

        return "hello springboot init...";
    }
}

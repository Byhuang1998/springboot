package com.byhuang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/11/2 20:59
 * @description TODO
 */
@RestController
public class HelloController {

    public static String basePath = "C:\\Users\\HBY\\Pictures\\";

    @RequestMapping("/hello")
    public String hello() {
        return "hello springboot...";
    }



    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            // 1、定义输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            // 2、通过response对象，获取到输出流
            ServletOutputStream outputStream = response.getOutputStream();

            // 3、通过response对象设置响应数据格式(image/jpeg)
            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                // 4、通过输入流读取文件数据，然后通过上述的输出流写回浏览器
                outputStream.write(bytes,0,len);
                // 刷新
                outputStream.flush();
            }

            // 5、关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description: 文件上传
     * @author: Jie
     * @date: 2022/8/15 10:16
     * @param: [file] 文件
     * @return: 文件名
     **/
    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        // 1、获取文件的原始文件名, 通过原始文件名获取文件后缀 例如：abc.jpg
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 2、使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;

        // 3、创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if (!dir.exists()) {
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            // 4、将临时文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}

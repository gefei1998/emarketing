package com.xjt.controller;

import com.xjt.common.Result;
import com.xjt.entity.common.UploadFileResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/api/upload")
public class UploadController {
    @CrossOrigin
    @RequestMapping("/uploadPhoto")
    @ResponseBody
    public Result uploadPhoto(@RequestParam(value="file") MultipartFile file) throws IOException {
        /*
         * 编码为字符串*/
        String s = Base64Utils.encodeToString(file.getBytes());

        /* *
         *2.解码成字节数组
         */
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(s);

        /*
         * 3.字节流转文件
         */
        String d = System.getProperty("user.dir");
        File fileMkdir = new File("e:\\bishe_Project\\qianduan\\VueDemo-master\\static\\upload");
        if (!fileMkdir.exists()){
            fileMkdir.mkdir();
        }
        String pathName = fileMkdir.getPath() + "\\" + file.getOriginalFilename();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathName);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Result result= new Result();
        result.setData("http://localhost:8090/static/upload/"+ file.getOriginalFilename());
        return result;
    }

    @CrossOrigin
    @RequestMapping("/uploadFile")
    @ResponseBody
    public UploadFileResponse uploadFile(@RequestParam(value="file") MultipartFile file) throws IOException {
        /*
         * 编码为字符串*/
        String s = Base64Utils.encodeToString(file.getBytes());

        /* *
         *2.解码成字节数组
         */
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(s);

        /*
         * 3.字节流转文件
         */
        String d = System.getProperty("user.dir");
        File fileMkdir = new File("e:\\bishe_Project\\qianduan\\VueDemo-master\\static\\upload");
        if (!fileMkdir.exists()){
            fileMkdir.mkdir();
        }
        String pathName = fileMkdir.getPath() + "\\" + file.getOriginalFilename();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathName);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        UploadFileResponse result= new UploadFileResponse();
        result.setLocation("http://localhost:8090/static/upload/"+ file.getOriginalFilename());
        return result;
    }
}


package com.itan.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.itan.sys.common.AppFileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("upload")
@RestController
public class FileController {
    /**
     * 图片上传
     * @param mf
     * @return
     */
    @RequestMapping("uploadFile")
    public Map<String,Object> uploadFile(MultipartFile mf){
        //1、获取文件名
        String filename = mf.getOriginalFilename();
        //2、生成新的文件名
        String newName= AppFileUtils.getNewFileName(filename);
        //3、获取当前日期
        String dirName= DateUtil.format(new Date(),"yyyy-MM-dd");
        //4、构造文件夹
        File dirFile=new File(AppFileUtils.UPLOAD_PATH,dirName);
        //5、判断文件夹是否存在
        if(!dirFile.exists()){
            dirFile.mkdir();//不存在就创建文件夹
        }
        //6、构造文件对象
        File file=new File(dirFile,newName+"_temp");
        //7、把图片信息写入file
        try {
            mf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("path",dirName+"/"+newName+"_temp");
        return map;
    }
    @RequestMapping("showImagesByPath")
    public ResponseEntity<Object> showImagesByPath(String path){
        return AppFileUtils.getResponseEntity(path);
    }
}

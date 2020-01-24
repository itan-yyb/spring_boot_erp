package com.itan.sys.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.IdUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 文件上传类
 */
public class AppFileUtils {
    //文件上传的默认路径
    public static String UPLOAD_PATH="E:/upload";
    //加载文件上传的配置路径的properties文件
    static {
        //读取配置文件存储地址
        InputStream in = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties=new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filepath = properties.getProperty("filepath");
        if(null!=filepath){
            UPLOAD_PATH=filepath;
        }
    }

    /**
     * 修改文件名,返回新的文件名
     * @param filename
     */
    public static String getNewFileName(String filename) {
        //获取文件后缀
        String suffix=filename.substring(filename.lastIndexOf("."),filename.length());
        return IdUtil.simpleUUID().toUpperCase()+suffix;
    }

    /**
     * 文件下载
     * @param path
     * @return
     */
    public static ResponseEntity<Object> getResponseEntity(String path) {
        //1、构建文件路径
        File file=new File(UPLOAD_PATH,path);
        if (file.exists()){
            //2、将下载的文件封装byte[]
            byte[] bytes=null;
            try {
                bytes= FileUtil.readBytes(file);
            } catch (IORuntimeException e) {
                e.printStackTrace();
            }
            //3、创建封装响应头信息的对象
            HttpHeaders headers=new HttpHeaders();
            //4、封装响应内容类型（APPLICATION_OCTET_STREAM 响应内容不限定）
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //5、设置下载的文件的名称
            headers.setContentDispositionFormData("attachment","123.jpg");
            //6、创建ResponseEntit对象
            ResponseEntity<Object> entity=new ResponseEntity<Object>(bytes,headers, HttpStatus.CREATED);
            return entity;
        }
        return null;
    }

    /**
     * 改名字,去掉_temp
     * @param goodsimg
     * @return
     */
    public static String reName(String goodsimg) {
        File file=new File(UPLOAD_PATH,goodsimg);
        String reName = goodsimg.replace("_temp", "");
        if (file.exists()){
            file.renameTo(new File(UPLOAD_PATH,reName));
        }
        return reName;
    }

    /**
     * 删除原照片
     * @param oldPath
     */
    public static void removeFileByPath(String oldPath) {
        if(!oldPath.equals(Contast.DEFAULT_IMG_PATH)){
            //创建文件对象
            File file=new File(UPLOAD_PATH,oldPath);
            //判断是否存在
            if(file.exists()){
                file.delete();
            }
        }
    }
}

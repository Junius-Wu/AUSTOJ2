package cn.edu.aust.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 关于文件操作的工具类
 */
public class FileUtil {
    /**
     * 保存文件到本地磁盘
     * @param dir 保存路径
     * @param filename 文件名
     * @param content 要保存的string字串
     */
    public static void saveToDisk(String dir,String filename,String content) throws IOException {
        File file = new File(dir,filename);
        if (!file.exists()){
            file.createNewFile();
        }
        FileUtils.writeStringToFile(file,content,"UTF-8");
    }

    /**
     * 保存用户上传头像到本地
     */
    public static void saveImgToDisk(String dir,String filename,MultipartFile pictureFile) throws IOException {
        File file = new File(dir,filename);
        if (!file.exists()){
            file.mkdirs();
        }
        pictureFile.transferTo(file);
    }



    /**
     * 测试主类
     * @param args
     */
    public static void main(String[] args) {
        try {
            saveToDisk("E://test/","haha.json","xixiixixi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

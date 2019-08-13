package nulll.skr.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUploadUtils {




    public String uploadFile(MultipartFile postImage,String imagePath){

        //处理图片
        String fileName = System.currentTimeMillis()+postImage.getOriginalFilename();
        String fileDestination = imagePath+fileName;
        System.out.println(fileDestination);
        File destFile = new File(fileDestination);
        destFile.getParentFile().mkdirs();
        try {
            postImage.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String fileUrl = "/image/"+fileName;

        return fileUrl;


    }


}

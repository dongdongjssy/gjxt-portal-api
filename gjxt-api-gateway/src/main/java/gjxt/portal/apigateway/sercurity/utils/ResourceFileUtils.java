package gjxt.portal.apigateway.sercurity.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.util.Random;

public class ResourceFileUtils {



    /**
     * 获取图片，由于spring boot打包成jar之后，获取到获取不到resources里头的图片，对此进行处理
     * @param path
     * targetFilePath temp/xx/tel.原文件后
     * @return
     * @author pangxianhe
     * @date 2020年1月2日
     */
    public static File queryFileTargetFile(String path,String targetFilePath,String name,int maxLength,String extName) {
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 获取远程服务器IP和端口
        try {
            //获取所有匹配的文件
            int randNum = new Random().nextInt(maxLength);
            path =  path+""+randNum+"."+extName;
            ClassPathResource resource = new ClassPathResource(path);
//            Resource resource = resolver.getResource(path);
            if(resource!=null){
                File ttfFile = new File( makeMkdirs(targetFilePath)+""+name+"."+extName);
                FileUtils.copyInputStreamToFile(resource.getInputStream(), ttfFile);
                return ttfFile;
            }else{
                return null;
            }

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String makeMkdirs(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getAbsolutePath();
            }
        }
        return filePath;
    }

    private static String getFileExtension(String fileName,String defualt) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return defualt;
    }

}

package io.renren.controller;

import io.renren.common.utils.R;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("seed-platform/upload")
public class UploadController {

    @Value("${file.uploadFolder}")
    private String localPath;
    @Value("${file.imageStaticAccessPath}")
    private String imageStaticAccessPath;
    @Value("${file.videoStaticAccessPath}")
    private String videoStaticAccessPath;
    @PostMapping("/pictureUpload")
    public R upload(@RequestParam("file") MultipartFile[] files) throws IOException {
        List<String> stringList = new ArrayList<>();
        for (MultipartFile file : files){

            if (file == null || file.isEmpty()){
              return R.error("上传文件为空");
            }
            String imageSuffix = FilenameUtils.getExtension(file.getOriginalFilename());
            assert imageSuffix != null;
            if (imageSuffix.equals("jpg") || imageSuffix.equals("png") || imageSuffix.equals("jpeg") || imageSuffix.equals("JPG")){
                String fileName = ((String) Objects.<String>requireNonNull(file.getOriginalFilename())).replace(" ", "");
                fileName = UUID.randomUUID().toString().replace("-","") + "." + imageSuffix;
                String realpath = localPath+"image/"+fileName;
                File dest = new File(realpath);
                if (!dest.getParentFile().exists()){
                    boolean mksucess;
                    mksucess =  dest.getParentFile().mkdirs();
                    if (!mksucess){
                        R.error("directory made failed");
                    }
                }
                file.transferTo(dest);
                String url = imageStaticAccessPath + fileName;
                stringList.add(url);
            }
        }
        return R.ok().put("fileList",stringList);
    }
    @PostMapping("/VideoUpload")
    public R uploadVideo(@RequestParam("file") MultipartFile[] files) throws IOException {
        String url = null;
        for (MultipartFile file : files){

            if (file == null || file.isEmpty()){
                return R.error("上传文件为空");
            }
            String imageSuffix = FilenameUtils.getExtension(file.getOriginalFilename());
            assert imageSuffix != null;
            if (imageSuffix.equals("MP4") || imageSuffix.equals("mp4") || imageSuffix.equals("ogg") || imageSuffix.equals("flv") || imageSuffix.equals("avi") || imageSuffix.equals("wmv")|| imageSuffix.equals("rmvb")){
                String fileName = ((String) Objects.<String>requireNonNull(file.getOriginalFilename())).replace(" ", "");
                fileName = UUID.randomUUID().toString().replace("-","") + "." + imageSuffix;
                String realpath = localPath+"video/"+fileName;
                File dest = new File(realpath);
                if (!dest.getParentFile().exists()){
                    boolean mksucess;
                    mksucess =  dest.getParentFile().mkdirs();
                    if (!mksucess){
                        R.error("directory made failed");
                    }
                }
                file.transferTo(dest);
                url = videoStaticAccessPath + fileName;
            }
        }
        return R.ok().put("fileList",url);
    }
}

package com.project.lotus.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UploadService {

    @Value("${file.dir}")
    private String fileDir;

    public String fileUpload(List<MultipartFile> images) throws IOException {

        // UUID로 고유 이미지 파일명 저장 *24.01.26 jihyun
        if (!images.isEmpty()) {
            for (MultipartFile multipartFile : images) {
                String uniqueFileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
                File saveImage = new File(fileDir, uniqueFileName);
                multipartFile.transferTo(saveImage);
            }
        }

        // 이미지 경로들 합칩. " "로 파일 구분 *24.01.26 jihyun
        StringBuilder imagesPaths = new StringBuilder();

        for (MultipartFile image : images) {
            String originalFilename = image.getOriginalFilename();
            imagesPaths.append(fileDir).append(originalFilename).append(" ");
        }

        return imagesPaths.toString().trim();
    }
}

package com.project.lotus.common.service;

import com.project.lotus.product.entity.Product;
import com.project.lotus.user.entity.Qna;
import com.project.lotus.user.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UploadService {

    @Value("${file.dir}")
    private String fileDir;

    public String fileUpload(List<MultipartFile> images) throws IOException {

        StringBuffer imagesPaths = new StringBuffer();

        // UUID로 고유 이미지 파일명 저장 *24.01.26 jihyun
        if (!images.isEmpty()) {
            for (MultipartFile multipartFile : images) {
                String uniqueFileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
                File saveImage = new File(fileDir, uniqueFileName);
                multipartFile.transferTo(saveImage);
                // 이미지 경로들 합칩. " "로 파일 구분 *24.01.26 jihyun
                imagesPaths.append(uniqueFileName).append(" ");
            }
        }

        return imagesPaths.toString().trim();
    }

    public String fileModify(List<MultipartFile> images, Product product) throws IOException {

        StringBuffer imagesPaths = new StringBuffer();

        // 합친 이미지 파일명 잘라냄 *24.02.14 jihyun
        String[] savedImages = product.getImages().split(" ");

        // 배열을 리스트로 변환 *24.02.14 jihyun
        List<String> list = Arrays.asList(savedImages);

        // 기존에 있던 이미지 파일이면 그대로 경로만 문자열로 저장, 새로운 파일이면 파일 저장 후 문자열로 저장 *24.02.14 jihyun
        for (MultipartFile image : images) {
            if (list.contains(image.getOriginalFilename())) {
                imagesPaths.append(image.getOriginalFilename()).append(" ");

            } else {
                String uniqueFileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                File saveImage = new File(fileDir, uniqueFileName);
                image.transferTo(saveImage);
                imagesPaths.append(uniqueFileName).append(" ");
            }
        }

        return imagesPaths.toString().trim();
    }

    public String fileModify(List<MultipartFile> images, Qna qna) throws IOException {

        StringBuffer imagesPaths = new StringBuffer();

        // 합친 이미지 파일명 잘라냄 *24.02.14 jihyun
        String[] savedImages = qna.getImages().split(" ");

        // 배열을 리스트로 변환 *24.02.14 jihyun
        List<String> list = Arrays.asList(savedImages);

        // 기존에 있던 이미지 파일이면 그대로 경로만 문자열로 저장, 새로운 파일이면 파일 저장 후 문자열로 저장 *24.02.14 jihyun
        for (MultipartFile image : images) {
            if (list.contains(image.getOriginalFilename())) {
                imagesPaths.append(image.getOriginalFilename()).append(" ");

            } else {
                String uniqueFileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                File saveImage = new File(fileDir, uniqueFileName);
                image.transferTo(saveImage);
                imagesPaths.append(uniqueFileName).append(" ");
            }
        }

        return imagesPaths.toString().trim();
    }

    public String fileModify(List<MultipartFile> images, Review review) throws IOException {
        
        StringBuffer imagesPaths = new StringBuffer();

        // 합친 이미지 파일명 잘라냄 *24.02.14 jihyun
        String[] savedImages = review.getImages().split(" ");

        // 배열을 리스트로 변환 *24.02.14 jihyun
        List<String> list = Arrays.asList(savedImages);

        // 기존에 있던 이미지 파일이면 그대로 경로만 문자열로 저장, 새로운 파일이면 파일 저장 후 문자열로 저장 *24.02.14 jihyun
        for (MultipartFile image : images) {

            if (list.contains(image.getOriginalFilename())) {
                imagesPaths.append(image.getOriginalFilename()).append(" ");

            } else {
                String uniqueFileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                File saveImage = new File(fileDir, uniqueFileName);
                image.transferTo(saveImage);
                imagesPaths.append(uniqueFileName).append(" ");
            }
        }

        return imagesPaths.toString().trim();
    }

    public String fileUpload (MultipartFile image) throws IOException {

        StringBuffer profilePath = new StringBuffer();

        if (!image.isEmpty()) {
            String uniqueProfileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            File saveImage = new File(fileDir, uniqueProfileName);
            image.transferTo(saveImage);
            profilePath.append(uniqueProfileName);
        }

        return profilePath.toString();
    }
}

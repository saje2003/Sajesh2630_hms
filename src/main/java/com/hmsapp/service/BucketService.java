package com.hmsapp.service;

import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class BucketService {

    @Autowired
    private AmazonS3 amazonS3;

    public String uploadFile(MultipartFile file, String bucketName) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }

        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        try {
            amazonS3.putObject(bucketName, convFile.getName(), convFile);
            return amazonS3.getUrl(bucketName, file.getOriginalFilename()).toString();
        } catch (AmazonS3Exception s3Exception) {
            return "Unable to upload file: " + s3Exception.getMessage();
        }
    }
}

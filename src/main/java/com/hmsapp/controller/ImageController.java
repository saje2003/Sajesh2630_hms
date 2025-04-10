package com.hmsapp.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.hmsapp.entity.User;
import com.hmsapp.service.BucketService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

       private BucketService bucketService;

    public ImageController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping(path = "/upload/file/{bucketName}/property/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<?> uploadPropertyPhotos(@RequestParam("file") MultipartFile file,
                                                      @PathVariable String bucketName,
                                                      @PathVariable long propertyId ) throws IOException {
            String imageUrl = bucketService.uploadFile(file, bucketName);
            return new ResponseEntity<>(imageUrl, HttpStatus.OK);
        }


    }




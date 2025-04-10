package com.hmsapp.controller;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.PropertyImage;
import com.hmsapp.repository.PropertyImageRepository;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.service.BucketService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyRepository propertyRepository;
    private BucketService bucketService;
    private PropertyImageRepository propertyImageRepository;

    public PropertyController(PropertyRepository propertyRepository, BucketService bucketService, PropertyImageRepository propertyImageRepository) {
        this.propertyRepository = propertyRepository;
        this.bucketService = bucketService;
        this.propertyImageRepository = propertyImageRepository;
    }

    @PostMapping("/addProperty")
    public String addProperty(
    ){
        return "added";
    }
    @DeleteMapping("/deleteProperty")
    public String deleteProperty(){
        return "delete";
    }

    //http://localhost:8080/api/v1/property/{searchParam}
    @GetMapping("/{searchParam}")
    public List <Property> searchProperty(
        @PathVariable String searchParam

    ){
      return propertyRepository.searchProperty(searchParam);
    }
    @PostMapping("/upload/file/{bucketName}/property/{propertyId}")
    public String uploadPropertyPhotos(
            @RequestParam("file") MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable long propertyId
    ) throws IOException {


    String imageUrl = bucketService.uploadFile(file, bucketName);
        PropertyImage propertyImage = new PropertyImage();
        propertyImage.setUrl(imageUrl);



        //set Fk
        Property property = propertyRepository.findById(propertyId).get();
        propertyImage.setProperty(property);

        propertyImageRepository.save(propertyImage);
        return "image is uploaded";
    }
    @GetMapping("/get/property/images")
    public List<PropertyImage> getPropertyImages(
            @RequestParam long id
    ){
        Property property = propertyRepository.findById(id).get();
        return  propertyImageRepository.findByProperty(property);
    }
}

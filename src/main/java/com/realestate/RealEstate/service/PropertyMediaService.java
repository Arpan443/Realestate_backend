package com.realestate.RealEstate.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.realestate.RealEstate.Entities.MediaType;
import com.realestate.RealEstate.Entities.Property;
import com.realestate.RealEstate.Entities.PropertyMedia;
import com.realestate.RealEstate.Repository.PropertyMediaRepo;
import com.realestate.RealEstate.Repository.PropertyRepo;
import com.realestate.RealEstate.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PropertyMediaService {

    private final Cloudinary cloudinary;
    private final PropertyRepo propertyRepo;
    private final PropertyMediaRepo propertyMediaRepo;

    public MediaResponse uploadMedia(Long propertyId, MultipartFile file) throws IOException {
        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        String contentType = file.getContentType();
        boolean isVideo = contentType != null && contentType.startsWith("video");

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("resource_type", isVideo ? "video" : "image")
        );

        String url = (String) uploadResult.get("secure_url");
        String publicId = (String) uploadResult.get("public_id");

        PropertyMedia media = PropertyMedia.builder()
                .url(url)
                .publicId(publicId)
                .mediaType(isVideo ? MediaType.VIDEO : MediaType.IMAGE)
                .property(property)
                .build();

        PropertyMedia saved = propertyMediaRepo.save(media);

        return MediaResponse.builder()
                .id(saved.getId())
                .url(saved.getUrl())
                .mediaType(saved.getMediaType().name())
                .build();
    }
}
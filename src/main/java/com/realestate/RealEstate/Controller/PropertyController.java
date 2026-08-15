package com.realestate.RealEstate.Controller;

import com.realestate.RealEstate.dto.MediaResponse;
import com.realestate.RealEstate.dto.PropertyRequest;
import com.realestate.RealEstate.dto.PropertyResponse;
import com.realestate.RealEstate.service.PropertyMediaService;
import com.realestate.RealEstate.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final PropertyMediaService propertyMediaService;

    @PostMapping
    public ResponseEntity<PropertyResponse> createProperty(@Valid @RequestBody PropertyRequest request) {
        String email = getCurrentUserEmail();
        return ResponseEntity.ok(propertyService.createProperty(request, email));
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getAllProperties(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        return ResponseEntity.ok(propertyService.searchProperties(city, type, minPrice, maxPrice));
    }
    @GetMapping("/my-listings")
    public ResponseEntity<List<PropertyResponse>> getMyListings() {
        String email = getCurrentUserEmail();
        return ResponseEntity.ok(propertyService.getPropertiesByOwner(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        String email = getCurrentUserEmail();
        propertyService.deleteProperty(id, email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/media")
    public ResponseEntity<MediaResponse> uploadMedia(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(propertyMediaService.uploadMedia(id, file));
    }

    private String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
package com.realestate.RealEstate.service;

import com.realestate.RealEstate.dto.PropertyRequest;
import com.realestate.RealEstate.dto.PropertyResponse;
import com.realestate.RealEstate.Entities.*;
import com.realestate.RealEstate.Repository.PropertyRepo;
import com.realestate.RealEstate.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepo propertyRepo;
    private final UserRepo userRepo;

    public PropertyResponse createProperty(PropertyRequest request, String ownerEmail) {
        User owner = userRepo.findByEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (owner.getRole() != Role.SELLER && owner.getRole() != Role.AGENT) {
            throw new RuntimeException("Only sellers or agents can create property listings");
        }

        Property property = Property.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .address(request.getAddress())
                .city(request.getCity())
                .type(PropertyType.valueOf(request.getType().toUpperCase()))
                .status(PropertyStatus.FOR_SALE)
                .bedrooms(request.getBedrooms())
                .bathrooms(request.getBathrooms())
                .area(request.getArea())
                .owner(owner)
                .build();

        Property saved = propertyRepo.save(property);
        return mapToResponse(saved);
    }

    public List<PropertyResponse> getAllProperties() {
        return propertyRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<PropertyResponse> searchProperties(String city, String type, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Property> properties = propertyRepo.findAll();

        return properties.stream()
                .filter(p -> city == null || p.getCity().equalsIgnoreCase(city))
                .filter(p -> type == null || p.getType().name().equalsIgnoreCase(type))
                .filter(p -> minPrice == null || p.getPrice().compareTo(minPrice) >= 0)
                .filter(p -> maxPrice == null || p.getPrice().compareTo(maxPrice) <= 0)
                .map(this::mapToResponse)
                .toList();
    }
    public List<PropertyResponse> getPropertiesByOwner(String email) {
        return propertyRepo.findAll()
                .stream()
                .filter(p -> p.getOwner().getEmail().equals(email))
                .map(this::mapToResponse)
                .toList();
    }

    public PropertyResponse getPropertyById(Long id) {
        Property property = propertyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        return mapToResponse(property);
    }

    public void deleteProperty(Long id, String requesterEmail) {
        Property property = propertyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!property.getOwner().getEmail().equals(requesterEmail)) {
            throw new RuntimeException("You can only delete your own listings");
        }

        propertyRepo.deleteById(id);
    }
    private PropertyResponse mapToResponse(Property property) {
        return PropertyResponse.builder()
                .id(property.getId())
                .title(property.getTitle())
                .description(property.getDescription())
                .price(property.getPrice())
                .address(property.getAddress())
                .city(property.getCity())
                .type(property.getType().name())
                .status(property.getStatus().name())
                .bedrooms(property.getBedrooms())
                .bathrooms(property.getBathrooms())
                .area(property.getArea())
                .ownerName(property.getOwner().getName())
                .ownerEmail(property.getOwner().getEmail())
                .mediaUrls(property.getMedia() == null ? List.of() :
                        property.getMedia().stream().map(PropertyMedia::getUrl).toList())
                .build();
    }

}
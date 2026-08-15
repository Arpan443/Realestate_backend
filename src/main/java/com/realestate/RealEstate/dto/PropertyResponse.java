package com.realestate.RealEstate.dto;



import lombok.Data;
import lombok.Builder;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PropertyResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String address;
    private String city;
    private String type;
    private String status;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double area;
    private Integer Sitting_space;
    private String ownerName;
    private String ownerEmail;
    private List<String> mediaUrls;
}
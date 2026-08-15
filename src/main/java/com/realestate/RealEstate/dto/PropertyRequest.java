package com.realestate.RealEstate.dto;



import lombok.Data;
import java.math.BigDecimal;

@Data
public class PropertyRequest {
    private String title;
    private String description;
    private BigDecimal price;
    private String address;
    private String city;
    private String type;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer Sitting_space;
    private Double area;
}
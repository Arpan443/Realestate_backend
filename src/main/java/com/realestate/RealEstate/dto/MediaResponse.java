package com.realestate.RealEstate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MediaResponse {
    private Long id;
    private String url;
    private String mediaType;
}

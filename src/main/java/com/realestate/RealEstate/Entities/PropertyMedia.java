package com.realestate.RealEstate.Entities;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "property_media")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String publicId;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}

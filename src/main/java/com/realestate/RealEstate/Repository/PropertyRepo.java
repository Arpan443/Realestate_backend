package com.realestate.RealEstate.Repository;



import com.realestate.RealEstate.Entities.Property;
import com.realestate.RealEstate.Entities.PropertyStatus;
import com.realestate.RealEstate.Entities.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PropertyRepo extends JpaRepository<Property, Long> {
    List<Property> findByCity(String city);
    List<Property> findByType(PropertyType type);
    List<Property> findByStatus(PropertyStatus status);
    Page<Property> findByOwnerId(Long ownerId, Pageable pageable);
}
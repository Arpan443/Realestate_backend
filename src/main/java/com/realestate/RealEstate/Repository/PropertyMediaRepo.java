package com.realestate.RealEstate.Repository;



import com.realestate.RealEstate.Entities.PropertyMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyMediaRepo extends JpaRepository<PropertyMedia, Long> {
    List<PropertyMedia> findByPropertyId(Long propertyId);
}

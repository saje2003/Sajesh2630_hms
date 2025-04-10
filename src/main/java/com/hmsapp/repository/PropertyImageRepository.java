package com.hmsapp.repository;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {

  List<PropertyImage> findByProperty(
          Property property
  );
}

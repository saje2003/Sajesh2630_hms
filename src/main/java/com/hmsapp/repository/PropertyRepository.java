package com.hmsapp.repository;

import com.hmsapp.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

  //jpql to search property based on city and country
  @Query("select p from Property p join p.city c join p.country co where co.name=:searchParam ")
  List<Property> searchProperty(
          @Param("searchParam") String searchParam
  );
}
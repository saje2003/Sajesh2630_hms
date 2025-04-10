package com.hmsapp.repository;

import com.hmsapp.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface CityRepository extends JpaRepository<City, Long> {
}
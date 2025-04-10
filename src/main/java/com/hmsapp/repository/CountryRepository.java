package com.hmsapp.repository;

import com.hmsapp.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
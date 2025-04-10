package com.hmsapp.repository;

import com.hmsapp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
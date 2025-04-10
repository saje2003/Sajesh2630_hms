package com.hmsapp.repository;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.Reviews;
import com.hmsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
         List<Reviews> findByUser(User user);
       Reviews  findByPropertyAndUser(Property property, User user );
}
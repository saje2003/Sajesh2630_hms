package com.hmsapp.repository;

import com.hmsapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
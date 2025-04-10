package com.hmsapp.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private Double balance;

        @Version
        private long version;  // Hibernate uses this field for optimistic locking

        // Constructors
        public Account() {}

        public Account(String name, Double balance) {
            this.name = name;
            this.balance = balance;
        }

        // Getters and Setters

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public long getVersion() {
            return version;
        }

    public void setVersion(long version) {
        this.version = version;
    }

    // No setter for version – Hibernate manages it automatically
    }



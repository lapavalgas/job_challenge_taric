package com.lapavalgas.taricchallenge.domain.repository;

import com.lapavalgas.taricchallenge.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}

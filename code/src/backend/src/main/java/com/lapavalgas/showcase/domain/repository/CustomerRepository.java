package com.lapavalgas.showcase.domain.repository;

import com.lapavalgas.showcase.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByCpf(String cpf);
}

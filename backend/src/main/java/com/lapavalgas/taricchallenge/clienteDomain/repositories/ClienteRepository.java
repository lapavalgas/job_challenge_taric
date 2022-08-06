package com.lapavalgas.taricchallenge.clienteDomain.repositories;

import com.lapavalgas.taricchallenge.clienteDomain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}

package com.lapavalgas.taricchallenge.clienteDomain.repositories;

import com.lapavalgas.taricchallenge.clienteDomain.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
}

package com.lapavalgas.taricchallenge.clienteDomain.repositories;

import com.lapavalgas.taricchallenge.clienteDomain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

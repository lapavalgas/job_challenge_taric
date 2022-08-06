package com.lapavalgas.taricchallenge.clienteDomain.repositories;

import com.lapavalgas.taricchallenge.clienteDomain.entities.EnderecoCEP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoCEPRepository extends JpaRepository<EnderecoCEP, Long>{

    boolean existsByCep(String cep);
    EnderecoCEP findByCep(String cep);

}

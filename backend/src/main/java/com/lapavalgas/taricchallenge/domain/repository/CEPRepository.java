package com.lapavalgas.taricchallenge.domain.repository;

import com.lapavalgas.taricchallenge.domain.model.CEP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CEPRepository extends JpaRepository<CEP, Long>{

    boolean existsByCep(String cep);
    CEP findByCep(String cep);

}

package com.senac.johnny.repository;

import com.senac.johnny.entity.ChamadaAtendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadaAtendenteRepository extends JpaRepository<ChamadaAtendente, Integer> {
}

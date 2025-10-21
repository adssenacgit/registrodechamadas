package com.senac.johnny.repository;

import com.senac.johnny.entity.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Integer> {

    Optional<Atendente> findByUsuarioLogin(String subject);
}

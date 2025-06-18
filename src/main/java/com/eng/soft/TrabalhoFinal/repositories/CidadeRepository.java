package com.eng.soft.TrabalhoFinal.repositories;

import com.eng.soft.TrabalhoFinal.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    // Métodos adicionais de consulta podem ser definidos aqui, se necessário
}

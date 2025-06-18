package com.eng.soft.TrabalhoFinal.repositories;

import com.eng.soft.TrabalhoFinal.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    // Métodos adicionais de consulta podem ser definidos aqui, se necessário
}

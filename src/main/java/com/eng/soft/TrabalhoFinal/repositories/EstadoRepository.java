package com.eng.soft.TrabalhoFinal.repositories;

import com.eng.soft.TrabalhoFinal.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    // Métodos adicionais de consulta podem ser definidos aqui, se necessário
}

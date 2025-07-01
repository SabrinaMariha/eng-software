package com.eng.soft.TrabalhoFinal.validacoes;

import com.eng.soft.TrabalhoFinal.model.DomainEntity;

public interface IStrategy<DomainEntity> {
    String processar(DomainEntity entity);
}

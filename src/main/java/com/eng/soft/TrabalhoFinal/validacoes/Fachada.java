package com.eng.soft.TrabalhoFinal.validacoes;

import com.eng.soft.TrabalhoFinal.model.DomainEntity;

import java.util.List;

public class Fachada implements IStrategy {
    //responsável por chamar uma lista de strategys
    //e executar as regras de negócio
    public void processar(DomainEntity entity) {
        // Aqui você pode implementar a lógica para chamar as regras de negócio
        // e executar as estratégias necessárias.
        // Por exemplo, você pode ter uma lista de IStrategy e iterar sobre ela,
        // chamando o método de execução de cada estratégia.

        // Exemplo:
        // List<IStrategy> strategies = getStrategies();
        // for (IStrategy strategy : strategies) {
        //     strategy.execute();
        // }
    }
}

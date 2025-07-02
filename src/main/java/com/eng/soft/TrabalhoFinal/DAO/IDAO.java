package com.eng.soft.TrabalhoFinal.DAO;



import com.eng.soft.TrabalhoFinal.model.Cliente;

import java.util.List;

public interface IDAO<DomainEntity> {

  void save (DomainEntity entity);
  void update (DomainEntity entity);
  DomainEntity findById(Long id) throws Exception;
  List<DomainEntity> findAll(DomainEntity entity);

  void updateSenha(DomainEntity entity);
  //void delete(DomainEntity entity);

}

package com.eng.soft.TrabalhoFinal.DAO;



import java.util.List;

public interface IDAO<DomainEntity> {

  void save (DomainEntity entity);
  void update (DomainEntity entity);
  DomainEntity findById(Long id) throws Exception;
  //List<DomainEntity> findAll();
  //void delete(DomainEntity entity);

}

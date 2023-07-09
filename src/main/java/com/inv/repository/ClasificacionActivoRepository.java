package com.inv.repository;

import com.inv.model.ClasificacionActivo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificacionActivoRepository extends CrudRepository<ClasificacionActivo, Long> {

}

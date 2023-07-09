package com.inv.repository;

import com.inv.model.ClasificacionActivo;
import com.inv.model.TasaDepreciacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasaDeprecicionRepository extends CrudRepository<TasaDepreciacion, Long> {
   List<TasaDepreciacion> findByClasificacionActivo(ClasificacionActivo clasificacionActivo);
}

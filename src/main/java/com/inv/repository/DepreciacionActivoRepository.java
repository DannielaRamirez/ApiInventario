package com.inv.repository;

import com.inv.model.DepreciacionActivo;
import org.springframework.data.repository.CrudRepository;

public interface DepreciacionActivoRepository extends CrudRepository<DepreciacionActivo, Long>{
    DepreciacionActivo findByActivoId(Long id);
}

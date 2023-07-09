package com.inv.repository;

import com.inv.model.Activo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoRepository extends CrudRepository<Activo, Long> {
}

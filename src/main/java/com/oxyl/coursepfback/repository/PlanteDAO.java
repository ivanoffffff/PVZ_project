package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.core.model.Plante;
import java.util.List;
import java.util.Optional;

public interface PlanteDAO {
    List<Plante> findAll();
    Optional<Plante> findById(Long id);
    List<Plante> findByEffet(String effet);
    Plante save(Plante plante);
    void update(Plante plante);
    void delete(Long id);
}
package com.oxyl.coursepfback.core.Service;

import com.oxyl.coursepfback.core.model.Plante;
import java.util.List;

public interface PlanteService {
    List<Plante> getAllPlantes();
    Plante getPlanteById(Long id);
    List<Plante> getPlantesByEffet(String effet);
    Plante createPlante(Plante plante);
    Plante updatePlante(Plante plante);
    void deletePlante(Long id);
}
package com.oxyl.coursepfback.core.Service;

import com.oxyl.coursepfback.repository.PlanteDAO;
import com.oxyl.coursepfback.core.model.Plante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanteServiceImpl implements PlanteService {

    private final PlanteDAO planteDAO;

    @Autowired
    public PlanteServiceImpl(PlanteDAO planteDAO) {
        this.planteDAO = planteDAO;
    }

    @Override
    public List<Plante> getAllPlantes() {
        return planteDAO.findAll();
    }

    @Override
    public Plante getPlanteById(Long id) {
        return planteDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Plante non trouvée avec l'ID: " + id));
    }

    @Override
    public List<Plante> getPlantesByEffet(String effet) {
        return planteDAO.findByEffet(effet);
    }

    @Override
    public Plante createPlante(Plante plante) {
        // Validation des données
        validatePlante(plante);

        return planteDAO.save(plante);
    }

    @Override
    public Plante updatePlante(Plante plante) {
        // Vérifier si la plante existe
        Plante existingPlante = getPlanteById(plante.getIdPlante());

        // Validation des données
        validatePlante(plante);

        planteDAO.update(plante);
        return plante;
    }

    @Override
    public void deletePlante(Long id) {
        // Vérifier si la plante existe
        getPlanteById(id);
        planteDAO.delete(id);
    }

    private void validatePlante(Plante plante) {
        if (plante.getNom() == null || plante.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la plante ne peut pas être vide");
        }

        if (plante.getPointDeVie() == null || plante.getPointDeVie() <= 0) {
            throw new IllegalArgumentException("Les points de vie doivent être positifs");
        }

        if (plante.getAttaqueParSeconde() == null || plante.getAttaqueParSeconde() < 0) {
            throw new IllegalArgumentException("L'attaque par seconde ne peut pas être négative");
        }

        if (plante.getDegatAttaque() == null || plante.getDegatAttaque() < 0) {
            throw new IllegalArgumentException("Les dégâts d'attaque ne peuvent pas être négatifs");
        }

        if (plante.getCout() == null || plante.getCout() < 0) {
            throw new IllegalArgumentException("Le coût ne peut pas être négatif");
        }

        if (plante.getSoleilParSeconde() == null) {
            throw new IllegalArgumentException("La valeur soleil par seconde ne peut pas être nulle");
        }

        if (plante.getEffet() == null ) {  //|| plante.getEffet().trim().isEmpty()
            throw new IllegalArgumentException("L'effet ne peut pas être vide");
        }

        if (plante.getCheminImage() == null || plante.getCheminImage().trim().isEmpty()) {
            throw new IllegalArgumentException("Le chemin de l'image ne peut pas être vide");
        }
    }
}
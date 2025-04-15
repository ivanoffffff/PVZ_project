package com.oxyl.coursepfback.core.Service;

import com.oxyl.coursepfback.repository.MapDAO;
import com.oxyl.coursepfback.core.model.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    private final MapDAO mapDAO;

    @Autowired
    public MapServiceImpl(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    @Override
    public List<Map> getAllMaps() {
        return mapDAO.findAll();
    }

    @Override
    public Map getMapById(Long id) {
        return mapDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Map non trouvée avec l'ID: " + id));
    }

    @Override
    public Map createMap(Map map) {
        // Validation des données
        validateMap(map);

        return mapDAO.save(map);
    }

    @Override
    public Map updateMap(Map map) {
        // Vérifier si la map existe
        Map existingMap = getMapById(map.getIdMap());

        // Validation des données
        validateMap(map);

        mapDAO.update(map);
        return map;
    }

    @Override
    public void deleteMap(Long id) {
        // Vérifier si la map existe
        getMapById(id);

        try {
            mapDAO.delete(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Impossible de supprimer la map : " + e.getMessage());
        }
    }

    private void validateMap(Map map) {
        if (map.getLigne() == null || map.getLigne() <= 0) {
            throw new IllegalArgumentException("Le nombre de lignes doit être positif");
        }

        if (map.getColonne() == null || map.getColonne() <= 0) {
            throw new IllegalArgumentException("Le nombre de colonnes doit être positif");
        }

        if (map.getCheminImage() == null || map.getCheminImage().trim().isEmpty()) {
            throw new IllegalArgumentException("Le chemin de l'image ne peut pas être vide");
        }
    }
}
package com.oxyl.coursepfback.core.Service;

import com.oxyl.coursepfback.repository.ZombieDAO;
import com.oxyl.coursepfback.repository.MapDAO;
import com.oxyl.coursepfback.core.model.Zombie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZombieServiceImpl implements ZombieService {

    private final ZombieDAO zombieDAO;
    private final MapDAO mapDAO;

    @Autowired
    public ZombieServiceImpl(ZombieDAO zombieDAO, MapDAO mapDAO) {
        this.zombieDAO = zombieDAO;
        this.mapDAO = mapDAO;
    }

    @Override
    public List<Zombie> getAllZombies() {
        return zombieDAO.findAll();
    }

    @Override
    public Zombie getZombieById(Long id) {
        return zombieDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Zombie non trouvé avec l'ID: " + id));
    }

    @Override
    public List<Zombie> getZombiesByMapId(Long mapId) {
        return zombieDAO.findByMapId(mapId);
    }

    @Override
    public Zombie createZombie(Zombie zombie) {
        // Validation des données
        validateZombie(zombie);

        // Vérifier si la map existe
        mapDAO.findById(zombie.getId_map())
                .orElseThrow(() -> new RuntimeException("Map non trouvée avec l'ID: " + zombie.getId_map()));

        return zombieDAO.save(zombie);
    }

    @Override
    public Zombie updateZombie(Zombie zombie) {
        // Vérifier si le zombie existe
        Zombie existingZombie = getZombieById(zombie.getId());

        // Validation des données
        validateZombie(zombie);

        // Vérifier si la map existe
        mapDAO.findById(zombie.getId_map())
                .orElseThrow(() -> new RuntimeException("Map non trouvée avec l'ID: " + zombie.getId_map()));

        zombieDAO.update(zombie);
        return zombie;
    }

    @Override
    public void deleteZombie(Long id) {
        // Vérifier si le zombie existe
        getZombieById(id);
        zombieDAO.delete(id);
    }

    private void validateZombie(Zombie zombie) {
        if (zombie.getNom() == null || zombie.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du zombie ne peut pas être vide");
        }

        if (zombie.getPoints_de_vie() == null || zombie.getPoints_de_vie() <= 0) {
            throw new IllegalArgumentException("Les points de vie doivent être positifs");
        }

        if (zombie.getAttaque_par_seconde() == null || zombie.getAttaque_par_seconde() < 0) {
            throw new IllegalArgumentException("L'attaque par seconde ne peut pas être négative");
        }

        if (zombie.getDegat_attaque() == null || zombie.getDegat_attaque() < 0) {
            throw new IllegalArgumentException("Les dégâts d'attaque ne peuvent pas être négatifs");
        }

        if (zombie.getVitesse_de_deplacement() == null || zombie.getVitesse_de_deplacement() <= 0) {
            throw new IllegalArgumentException("La vitesse de déplacement doit être positive");
        }

        if (zombie.getChemin_image() == null || zombie.getChemin_image().trim().isEmpty()) {
            throw new IllegalArgumentException("Le chemin de l'image ne peut pas être vide");
        }

        if (zombie.getId_map() == null) {
            throw new IllegalArgumentException("Un zombie doit être associé à une map");
        }
    }
}
package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.core.model.Zombie;
import java.util.List;
import java.util.Optional;

public interface ZombieDAO {
    List<Zombie> findAll();
    Optional<Zombie> findById(Long id);
    List<Zombie> findByMapId(Long mapId);
    Zombie save(Zombie zombie);
    void update(Zombie zombie);
    void delete(Long id);
}
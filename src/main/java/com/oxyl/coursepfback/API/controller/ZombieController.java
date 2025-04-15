package com.oxyl.coursepfback.API.controller;

import com.oxyl.coursepfback.API.DTO.ZombieDTO;
import com.oxyl.coursepfback.core.Service.ZombieService;
import com.oxyl.coursepfback.core.model.Zombie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zombies")
@CrossOrigin(origins = "*")
public class ZombieController {

    private final ZombieService zombieService;

    @Autowired
    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @GetMapping
    public ResponseEntity<List<ZombieDTO>> getAllZombies() {
        List<Zombie> zombies = zombieService.getAllZombies();
        List<ZombieDTO> zombieDTOs = zombies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(zombieDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZombieDTO> getZombieById(@PathVariable("id") Long id) {
        try {
            Zombie zombie = zombieService.getZombieById(id);
            return ResponseEntity.ok(convertToDTO(zombie));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/map/{mapId}")
    public ResponseEntity<List<ZombieDTO>> getZombiesByMapId(@PathVariable("mapId") Long mapId) {
        List<Zombie> zombies = zombieService.getZombiesByMapId(mapId);
        List<ZombieDTO> zombieDTOs = zombies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(zombieDTOs);
    }

    @PostMapping
    public ResponseEntity<ZombieDTO> createZombie(@RequestBody ZombieDTO zombieDTO) {
        try {
            Zombie zombie = convertToEntity(zombieDTO);
            Zombie createdZombie = zombieService.createZombie(zombie);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(createdZombie));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZombieDTO> updateZombie(@PathVariable("id") Long id, @RequestBody ZombieDTO zombieDTO) {
        try {
            zombieDTO.setId(id); // Assurer que l'ID dans le DTO est celui du path
            Zombie zombie = convertToEntity(zombieDTO);
            Zombie updatedZombie = zombieService.updateZombie(zombie);
            return ResponseEntity.ok(convertToDTO(updatedZombie));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZombie(@PathVariable("id") Long id) {
        try {
            zombieService.deleteZombie(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private ZombieDTO convertToDTO(Zombie zombie) {
        return new ZombieDTO(
                zombie.getId(),
                zombie.getNom(),
                zombie.getPoints_de_vie(),
                zombie.getAttaque_par_seconde(),
                zombie.getDegat_attaque(),
                zombie.getVitesse_de_deplacement(),
                zombie.getChemin_image(),
                zombie.getId_map()
        );
    }

    private Zombie convertToEntity(ZombieDTO dto) {
        return new Zombie(
                dto.getId(),
                dto.getNom(),
                dto.getPointDeVie(),
                dto.getAttaqueParSeconde(),
                dto.getDegatAttaque(),
                dto.getVitesseDeDeplacement(),
                dto.getCheminImage(),
                dto.getMapId()
        );
    }
}
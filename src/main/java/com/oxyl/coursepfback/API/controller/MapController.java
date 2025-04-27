package com.oxyl.coursepfback.API.controller;

import com.oxyl.coursepfback.API.DTO.MapDTO;
import com.oxyl.coursepfback.API.DTO.ZombieDTO;
import com.oxyl.coursepfback.core.Service.MapService;
import com.oxyl.coursepfback.core.model.Map;
import com.oxyl.coursepfback.core.model.Zombie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/maps")
@CrossOrigin(origins = "*")
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping
    public ResponseEntity<List<MapDTO>> getAllMaps() {
        List<Map> maps = mapService.getAllMaps();
        List<MapDTO> mapDTOs = maps.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mapDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapDTO> getMapById(@PathVariable("id") Long id) {
        try {
            Map map = mapService.getMapById(id);
            return ResponseEntity.ok(convertToDTO(map));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MapDTO> createMap(@RequestBody MapDTO mapDTO) {
        try {
            Map map = convertToEntity(mapDTO);
            Map createdMap = mapService.createMap(map);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(createdMap));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MapDTO> updateMap(@PathVariable("id") Long id, @RequestBody MapDTO updatedMapDTO) {
        Map existingMap = mapService.getMapById(id);
        if (existingMap == null) {
            return ResponseEntity.notFound().build();
        }
        if (updatedMapDTO.getLigne() != null) {
            existingMap.setLigne(updatedMapDTO.getLigne());
        }
        if (updatedMapDTO.getColonne() != null) {
            existingMap.setColonne(updatedMapDTO.getColonne());
        }
        if (updatedMapDTO.getCheminImage() != null) {
            existingMap.setCheminImage(updatedMapDTO.getCheminImage());
        }
        Map savedMap = mapService.updateMap(existingMap);
        MapDTO responseDTO = convertToDTO(savedMap);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMap(@PathVariable("id") Long id) {
        try {
            mapService.deleteMap(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private MapDTO convertToDTO(Map map) {
        MapDTO dto = new MapDTO(
                map.getIdMap(),
                map.getLigne(),
                map.getColonne(),
                map.getCheminImage()
        );

        // Convertir les zombies si présents
        if (map.getZombies() != null && !map.getZombies().isEmpty()) {
            List<ZombieDTO> zombieDTOs = map.getZombies().stream()
                    .map(this::convertZombieToDTO)
                    .collect(Collectors.toList());
            dto.setZombies(zombieDTOs);
        }

        return dto;
    }

    private Map convertToEntity(MapDTO dto) {
        Map map = new Map(
                dto.getId(),
                dto.getLigne(),
                dto.getColonne(),
                dto.getCheminImage()
        );

        // Les zombies sont généralement gérés par les endpoints de Zombie, pas ici

        return map;
    }

    private ZombieDTO convertZombieToDTO(Zombie zombie) {
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
}
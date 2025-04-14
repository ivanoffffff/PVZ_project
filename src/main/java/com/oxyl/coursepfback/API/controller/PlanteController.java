package com.oxyl.coursepfback.API.controller;

import com.oxyl.coursepfback.API.DTO.PlanteDTO;
import com.oxyl.coursepfback.core.Service.PlanteService;
import com.oxyl.coursepfback.core.model.Plante;
import com.oxyl.coursepfback.core.model.Effet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/plantes")
@CrossOrigin(origins = "*")
public class PlanteController {

    private final PlanteService planteService;

    @Autowired
    public PlanteController(PlanteService planteService) {
        this.planteService = planteService;
    }

    @GetMapping
    public ResponseEntity<List<PlanteDTO>> getAllPlantes() {
        List<Plante> plantes = planteService.getAllPlantes();
        List<PlanteDTO> planteDTOs = plantes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(planteDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanteDTO> getPlanteById(@PathVariable Long id) {
        try {
            Plante plante = planteService.getPlanteById(id);
            return ResponseEntity.ok(convertToDTO(plante));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/effet/{effet}")
    public ResponseEntity<List<PlanteDTO>> getPlantesByEffet(@PathVariable String effet) {
        List<Plante> plantes = planteService.getPlantesByEffet(effet);
        List<PlanteDTO> planteDTOs = plantes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(planteDTOs);
    }

    @PostMapping
    public ResponseEntity<PlanteDTO> createPlante(@RequestBody PlanteDTO planteDTO) {
        try {
            Plante plante = convertToEntity(planteDTO);
            Plante createdPlante = planteService.createPlante(plante);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(createdPlante));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanteDTO> updatePlante(@PathVariable Long id, @RequestBody PlanteDTO planteDTO) {
        try {
            planteDTO.setId(id); // Assurer que l'ID dans le DTO est celui du path
            Plante plante = convertToEntity(planteDTO);
            Plante updatedPlante = planteService.updatePlante(plante);
            return ResponseEntity.ok(convertToDTO(updatedPlante));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlante(@PathVariable Long id) {
        try {
            planteService.deletePlante(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private PlanteDTO convertToDTO(Plante plante) {
        return new PlanteDTO(
                plante.getIdPlante(),
                plante.getNom(),
                plante.getPointDeVie(),
                plante.getAttaqueParSeconde(),
                plante.getDegatAttaque(),
                plante.getCout(),
                plante.getSoleilParSeconde(),
                plante.getEffet() != null ? plante.getEffet().toString().toLowerCase().replace("_", " ") : "normal",
                plante.getCheminImage()
        );
    }

    private Plante convertToEntity(PlanteDTO dto) {
        // Conversion de la chaîne effet en enum
        Effet effet = Effet.NORMAL; // Valeur par défaut
        if (dto.getEffet() != null) {
            try {
                effet = Effet.valueOf(dto.getEffet().toUpperCase().replace(" ", "_"));
            } catch (IllegalArgumentException e) {
                // En cas d'erreur, on garde la valeur par défaut
            }
        }

        return new Plante(
                dto.getId(),
                dto.getNom(),
                dto.getPointDeVie(),
                dto.getAttaqueParSeconde(),
                dto.getDegatAttaque(),
                dto.getCout(),
                dto.getSoleilParSeconde(),
                effet,
                dto.getCheminImage()
        );
    }
}

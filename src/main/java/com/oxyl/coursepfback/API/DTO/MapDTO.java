package com.oxyl.coursepfback.API.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MapDTO {
    @JsonProperty("id_map")
    private Long id;
    private Integer ligne;
    private Integer colonne;
    private String cheminImage;
    private List<ZombieDTO> zombies;

    // Constructeurs
    public MapDTO() {}

    public MapDTO(Long id, Integer ligne, Integer colonne, String cheminImage) {
        this.id = id;
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = cheminImage;
    }

    // Constructeur avec zombies
    public MapDTO(Long id, Integer ligne, Integer colonne, String cheminImage, List<ZombieDTO> zombies) {
        this.id = id;
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = cheminImage;
        this.zombies = zombies;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLigne() {
        return ligne;
    }

    public void setLigne(Integer ligne) {
        this.ligne = ligne;
    }

    public Integer getColonne() {
        return colonne;
    }

    public void setColonne(Integer colonne) {
        this.colonne = colonne;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public List<ZombieDTO> getZombies() {
        return zombies;
    }

    public void setZombies(List<ZombieDTO> zombies) {
        this.zombies = zombies;
    }
}
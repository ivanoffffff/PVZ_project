package com.oxyl.coursepfback.core.model;

import java.util.List;

public class Map {

    private Long id_map;
    private Integer ligne;
    private Integer colonne;
    private String chemin_image;
    private List<Zombie> zombies; // Relation avec Zombie (one-to-many)

    // Constructeurs
    public Map() {}

    public Map(Long id_map, int ligne, int colonne, String chemin_image) {
        this.id_map = id_map;
        this.ligne = ligne;
        this.colonne = colonne;
         this.chemin_image = chemin_image;
    }

    // Getters et Setters
    public Long getIdMap() {
        return id_map;
    }

    public void setIdMap(Long idMap) {
        this.id_map = idMap;
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
        return chemin_image;
    }

    public void setCheminImage(String cheminImage) {
        this.chemin_image = cheminImage;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(List<Zombie> zombies) {
        this.zombies = zombies;
    }

    @Override
    public String toString() {
        return "Map{" +
                "id=" + id_map +
                ", ligne='" + ligne + '\'' +
                ", colonne=" + colonne +
                ", img=" + chemin_image +
                '}';
    }
}

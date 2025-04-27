package com.oxyl.coursepfback.API.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZombieDTO {
    @JsonProperty("id_zombie")
    private Long id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("point_de_vie")
    private Integer pointDeVie;

    @JsonProperty("attaque_par_seconde")
    private Double attaqueParSeconde;

    @JsonProperty("degat_attaque")
    private Integer degatAttaque;

    @JsonProperty("vitesse_de_deplacement")
    private Double vitesseDeDeplacement;

    @JsonProperty("chemin_image")
    private String cheminImage;

    @JsonProperty("id_map")
    private Long idMap;

    // Constructeurs
    public ZombieDTO() {}

    public ZombieDTO(Long id, String nom, Integer pointDeVie, Double attaqueParSeconde,
                     Integer degatAttaque, Double vitesseDeDeplacement,
                     String cheminImage, Long mapId) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.vitesseDeDeplacement = vitesseDeDeplacement;
        this.cheminImage = cheminImage;
        this.idMap= mapId;
    }

    public ZombieDTO(Long id, String nom, Integer pointDeVie, Double attaqueParSeconde,
                     Integer degatAttaque, Double vitesseDeDeplacement,
                     String cheminImage) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.vitesseDeDeplacement = vitesseDeDeplacement;
        this.cheminImage = cheminImage;
        this.idMap = null;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPointDeVie() {
        return pointDeVie;
    }

    public void setPointDeVie(Integer pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public Double getAttaqueParSeconde() {
        return attaqueParSeconde;
    }

    public void setAttaqueParSeconde(Double attaqueParSeconde) {
        this.attaqueParSeconde = attaqueParSeconde;
    }

    public Integer getDegatAttaque() {
        return degatAttaque;
    }

    public void setDegatAttaque(Integer degatAttaque) {
        this.degatAttaque = degatAttaque;
    }

    public Double getVitesseDeDeplacement() {
        return vitesseDeDeplacement;
    }

    public void setVitesseDeDeplacement(Double vitesseDeDeplacement) {
        this.vitesseDeDeplacement = vitesseDeDeplacement;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public Long getMapId() {
        return idMap;
    }

    public void setMapId(Long mapId) {
        this.idMap = mapId;
    }
}
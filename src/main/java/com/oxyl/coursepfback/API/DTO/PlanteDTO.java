package com.oxyl.coursepfback.API.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oxyl.coursepfback.core.model.Effet;

public class PlanteDTO {
    @JsonProperty("id_plante")
    private Long id_plante;
    private String nom;
    private Integer point_de_vie;
    private Double attaqueParSeconde;
    private Integer degatAttaque;
    private Integer cout;
    private Double soleilParSeconde;
    private String effet; // On garde une chaîne pour le DTO pour simplifier la sérialisation JSON
    private String cheminImage;

    // Constructeurs
    public PlanteDTO() {}

    public PlanteDTO(Long id, String nom, Integer pointDeVie, Double attaqueParSeconde,
                     Integer degatAttaque, Integer cout, Double soleilParSeconde,
                     String effet, String cheminImage) {
        this.id_plante = id;
        this.nom = nom;
        this.point_de_vie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.cout = cout;
        this.soleilParSeconde = soleilParSeconde;
        this.effet = effet;
        this.cheminImage = cheminImage;
    }

    // Getters et Setters
    public Long getId() {
        return id_plante;
    }

    public void setId(Long id) {
        this.id_plante = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPointDeVie() {
        return point_de_vie;
    }

    public void setPointDeVie(Integer pointDeVie) {
        this.point_de_vie = pointDeVie;
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

    public Integer getCout() {
        return cout;
    }

    public void setCout(Integer cout) {
        this.cout = cout;
    }

    public Double getSoleilParSeconde() {
        return soleilParSeconde;
    }

    public void setSoleilParSeconde(Double soleilParSeconde) {
        this.soleilParSeconde = soleilParSeconde;
    }

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
}
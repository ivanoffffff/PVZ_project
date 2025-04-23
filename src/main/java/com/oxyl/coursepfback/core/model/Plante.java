package com.oxyl.coursepfback.core.model;

public class Plante {
    private Long id_plante;
    private String nom;
    private Integer points_de_vie;
    private Double attaque_par_seconde;
    private Integer degat_attaque;
    private Integer cout;
    private Double soleil_par_seconde;
    private Effet effet;
    private String chemin_image;

    // Constructeurs

    public Plante() {}

    public Plante(Long id, String nom, int pointsDeVie, Double attaque_par_seconde, int degat_attaque, int cout, Double soleil_par_seconde, Effet effet, String chemin_image) {
        this.id_plante = id;
        this.nom = nom;
        this.points_de_vie = pointsDeVie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.cout = cout;
        this.soleil_par_seconde = soleil_par_seconde;
        this.effet = effet;
        this.chemin_image = chemin_image;
    }

    // Getters et Setters
    public Long getIdPlante() {
        return id_plante;
    }

    public void setIdPlante(Long idPlante) {
        this.id_plante = idPlante;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPointDeVie() {
        return points_de_vie;
    }

    public void setPointDeVie(Integer pointDeVie) {
        this.points_de_vie = pointDeVie;
    }

    public Double getAttaqueParSeconde() {
        return attaque_par_seconde;
    }

    public void setAttaqueParSeconde(Double attaqueParSeconde) {
        this.attaque_par_seconde = attaqueParSeconde;
    }

    public Integer getDegatAttaque() {
        return degat_attaque;
    }

    public void setDegatAttaque(Integer degatAttaque) {
        this.degat_attaque = degatAttaque;
    }

    public Integer getCout() {
        return cout;
    }

    public void setCout(Integer cout) {
        this.cout = cout;
    }

    public Double getSoleilParSeconde() {
        return soleil_par_seconde;
    }

    public void setSoleilParSeconde(Double soleilParSeconde) {
        this.soleil_par_seconde = soleilParSeconde;
    }

    public Effet getEffet() {
        return effet;
    }

    public void setEffet(Effet effet) {
        this.effet = effet;
    }

    public String getCheminImage() {
        return chemin_image;
    }

    public void setCheminImage(String cheminImage) {
        this.chemin_image = cheminImage;
    }


    @Override
    public String toString() {
        return "Plante{" +
                "id_plante=" + id_plante +
                ", nom='" + nom + '\'' +
                ", points_de_vie=" + points_de_vie +
                ", attaque_par_seconde=" + attaque_par_seconde +
                '}';
    }
}

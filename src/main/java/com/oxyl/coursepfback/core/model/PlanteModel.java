package com.oxyl.coursepfback.core.model;

public class PlanteModel {
    private int id_plante;
    private String nom;
    private int points_de_vie;
    private float attaque_par_seconde;
    private int degat_attaque;
    private int cout;
    private float soleil_par_seconde;
    private Effet effet;
    private String chemin_image;

    public PlanteModel(int ed, String nom, int pointsDeVie, int puissanceAttaque, int degat_attaque, int cout, float soleil_par_seconde, String chemin_image) {
        this.id_plante = ed;
        this.nom = nom;
        this.points_de_vie = pointsDeVie;
        this.attaque_par_seconde = puissanceAttaque;
        this.degat_attaque = degat_attaque;
        this.cout = cout;
        this.soleil_par_seconde = soleil_par_seconde;
        this.effet = effet;
        this.chemin_image = chemin_image;
    }

    public int getId_plante() { return id_plante; }
    public void setId_plante(int id_plante) { this.id_plante = id_plante; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getPoints_de_vie() { return points_de_vie; }
    public void setPoints_de_vie(int points_de_vie) { this.points_de_vie = points_de_vie; }

    public float getAttaque_par_seconde() { return attaque_par_seconde; }
    public void setAttaque_par_seconde(int attaque_par_seconde) { this.attaque_par_seconde = attaque_par_seconde; }

    public int getDegat_attaque() { return degat_attaque; }
    public void setDegat_attaque(int attaque) { this.degat_attaque = attaque; }

    public int getCout() { return cout; }
    public void setCout(int cout) { this.cout = cout; }

    public float getSoleil_par_seconde() { return soleil_par_seconde; }
    public void setSoleil_par_seconde(float soleil_par_seconde) {this.soleil_par_seconde = soleil_par_seconde; }

    public Effet getEffet() { return effet; }
    public void setEffet(Effet effet) {this.effet = effet; }

    public String getChemin_image() { return chemin_image; }
    public void setChemin_image(String chemin_image) {this.chemin_image = chemin_image; }


    @Override
    public String toString() {
        return "Plante{" +
                "id=" + id_plante +
                ", nom='" + nom + '\'' +
                ", pointsDeVie=" + points_de_vie +
                ", puissanceAttaque=" + attaque_par_seconde +
                '}';
    }
}

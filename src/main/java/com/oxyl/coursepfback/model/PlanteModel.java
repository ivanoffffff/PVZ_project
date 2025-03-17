package com.oxyl.coursepfback.model;

public class PlanteModel {
    private int id;
    private String nom;
    private int pointsDeVie;
    private int puissanceAttaque;

    public PlanteModel(int id, String nom, int pointsDeVie, int puissanceAttaque) {
        this.id = id;
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.puissanceAttaque = puissanceAttaque;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getPointsDeVie() { return pointsDeVie; }
    public void setPointsDeVie(int pointsDeVie) { this.pointsDeVie = pointsDeVie; }

    public int getPuissanceAttaque() { return puissanceAttaque; }
    public void setPuissanceAttaque(int puissanceAttaque) { this.puissanceAttaque = puissanceAttaque; }

    @Override
    public String toString() {
        return "Plante{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pointsDeVie=" + pointsDeVie +
                ", puissanceAttaque=" + puissanceAttaque +
                '}';
    }
}

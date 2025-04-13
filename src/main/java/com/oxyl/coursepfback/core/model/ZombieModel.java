package com.oxyl.coursepfback.core.model;

public class ZombieModel {

    private int id;
    private String type;
    private int pointsDeVie;
    private int vitesse;

    public ZombieModel(int id, String type, int pointsDeVie, int vitesse) {
        this.id = id;
        this.type = type;
        this.pointsDeVie = pointsDeVie;
        this.vitesse = vitesse;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getPointsDeVie() { return pointsDeVie; }
    public void setPointsDeVie(int pointsDeVie) { this.pointsDeVie = pointsDeVie; }

    public int getVitesse() { return vitesse; }
    public void setVitesse(int vitesse) { this.vitesse = vitesse; }

    @Override
    public String toString() {
        return "Zombie{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", pointsDeVie=" + pointsDeVie +
                ", vitesse=" + vitesse +
                '}';
    }
}

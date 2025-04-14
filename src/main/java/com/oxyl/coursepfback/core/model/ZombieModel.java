package com.oxyl.coursepfback.core.model;

public class ZombieModel {

    private int id_zombie;
    private String nom;
    private int points_de_vie;
    private float attaque_par_seconde;
    private int degat_attaque;
    private float vitesse_de_deplacement;
    private String chemin_image;
    private int id_map;

    public ZombieModel(int id_zombie, String nom, int points_de_vie, float attaque_par_seconde, int degat_attaque, float vitesse_de_deplacement, String chemin_image, int id_map) {
        this.id_zombie = id_zombie;
        this.nom = nom;
        this.points_de_vie = points_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.vitesse_de_deplacement = vitesse_de_deplacement;
        this.chemin_image = chemin_image;
        this.id_map = id_map;
    }

    public int getId() { return id_zombie; }
    public void setId(int id) { this.id_zombie = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getPoints_de_vie() { return points_de_vie; }
    public void setPoints_de_vie(int points_de_vie) { this.points_de_vie = points_de_vie; }

    public float getAttaque_par_seconde() { return attaque_par_seconde; }
    public void setAttaque_par_seconde(int attaque_par_seconde) { this.attaque_par_seconde = attaque_par_seconde; }

    public int getDegat_attaque() { return degat_attaque; }
    public void setDegat_attaque(int degat_attaque) { this.degat_attaque = degat_attaque; }

    public float getVitesse_de_deplacement() {return vitesse_de_deplacement; }
    public void setVitesse_de_deplacement(float vitesse_de_deplacement) {this.vitesse_de_deplacement = vitesse_de_deplacement;}

    public String getChemin_image() { return chemin_image; }
    public void setChemin_image(String chemin_image) {this.chemin_image = chemin_image; }

    public int getId_map() { return id_map; }
    public void setId_map(int id_map) { this.id_map = id_map; }

    @Override
    public String toString() {
        return "Zombie{" +
                "id=" + id_zombie +
                ", type='" + nom + '\'' +
                ", pointsDeVie=" + points_de_vie +
                ", vitesseAttaque=" + attaque_par_seconde +
                ", degatAttaque=" + degat_attaque +
                ", vitesseDeDeplacement=" + vitesse_de_deplacement +
                ", cheminImage='" + chemin_image + '\'' +
                ", id_map=" + id_map +
                '}';
    }
}

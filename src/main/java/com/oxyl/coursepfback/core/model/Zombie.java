package com.oxyl.coursepfback.core.model;


public class Zombie {

    private Long id_zombie;
    private String nom;
    private Integer points_de_vie;
    private Double attaque_par_seconde;
    private Integer degat_attaque;
    private Double vitesse_de_deplacement;
    private String chemin_image;
    private Long id_map;
    private Map map; // Relation avec Map


    // Constructeurs
    public Zombie() {}

    public Zombie(Long id_zombie, String nom, int points_de_vie, Double attaque_par_seconde, int degat_attaque, Double vitesse_de_deplacement, String chemin_image, Long id_map) {
        this.id_zombie = id_zombie;
        this.nom = nom;
        this.points_de_vie = points_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.vitesse_de_deplacement = vitesse_de_deplacement;
        this.chemin_image = chemin_image;
        this.id_map = id_map;
    }

    // Getters et Setters

    public Long getId() { return id_zombie; }
    public void setId(Long id) { this.id_zombie = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getPoints_de_vie() { return points_de_vie; }
    public void setPoints_de_vie(int points_de_vie) { this.points_de_vie = points_de_vie; }

    public Double getAttaque_par_seconde() { return attaque_par_seconde; }
    public void setAttaque_par_seconde(Double attaque_par_seconde) { this.attaque_par_seconde = attaque_par_seconde; }

    public Integer getDegat_attaque() { return degat_attaque; }
    public void setDegat_attaque(int degat_attaque) { this.degat_attaque = degat_attaque; }

    public Double getVitesse_de_deplacement() { return vitesse_de_deplacement; }
    public void setVitesse_de_deplacement(Double vitesse_de_deplacement) { this.vitesse_de_deplacement = vitesse_de_deplacement; }

    public String getChemin_image() { return chemin_image; }
    public void setChemin_image(String chemin_image) {this.chemin_image = chemin_image; }

    public Long getId_map() { return id_map; }
    public void setId_map(Long id_map) { this.id_map = id_map; }

    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map; }

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

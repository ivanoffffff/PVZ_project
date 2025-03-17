package com.oxyl.coursepfback.model;

import java.util.List;

public class MapModel {

    private int id_map;
    private int ligne;
    private int colonne;
    private String chemin_image;
    public MapModel(int id_map, int ligne, int colonne, String chemin_image) {
        this.id_map = id_map;
        this.ligne = ligne;
        this.colonne = colonne;
         this.chemin_image = chemin_image;
    }

    public int getId() { return id_map; }
    public void setId(int id) { this.id_map = id; }

    public int getLigne() { return ligne; }
    public void setNom(int ligne) { this.ligne = ligne; }

    public int getColonne() { return colonne; }
    public void setColonne(int colonne) { this.colonne = colonne; }

    public String getChemin_image() { return chemin_image; }
    public void setChemin_image(String chemin_image) { this.chemin_image = chemin_image; }


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

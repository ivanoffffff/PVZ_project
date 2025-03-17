package com.oxyl.coursepfback.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String nom;

    // Constructeur
    public User(int id, String username, String email, String nom) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nom = nom;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Object getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}

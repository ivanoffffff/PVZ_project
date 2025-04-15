# Plants vs Zombies - API Backend
Ce projet est une implémentation backend d'une API REST pour un jeu inspiré de Plants vs Zombies, permettant la gestion des zombies, des plantes et des cartes via des opérations CRUD.
## Technologies utilisées

Langage: Java 21
Framework: Spring MVC
Base de données: MySQL
Outil d'accès aux données: JdbcTemplate
Serveur d'application: Tomcat 10
Gestion des dépendances: Maven

## Structure du projet
Le projet suit une architecture en couches :

Modèles (core/model/) : Représentation des entités de la base de données
DAOs (repository/) : Accès aux données via JdbcTemplate
Services (core/Service/) : Logique métier
DTOs (API/DTO/) : Objets de transfert de données pour l'API
Contrôleurs (API/controller/) : Points d'entrée REST

## Configuration
Le projet est configuré pour utiliser une base de données MySQL locale. Les paramètres de connexion se trouvent dans la classe DatabaseConfig.
## Installation et déploiement

Cloner le repository
Configurer une base de données MySQL avec les scripts fournis (init.sql et values.sql)
Configurer les paramètres de connexion dans DatabaseConfig.java
Compiler le projet avec Maven : mvn clean package
Déployer le WAR généré sur Tomcat

## Endpoints API
### Zombies

GET /api/zombies : Récupérer tous les zombies
GET /api/zombies/{id} : Récupérer un zombie par son ID
GET /api/zombies/map/{mapId} : Récupérer les zombies par carte
POST /api/zombies : Créer un zombie
PUT /api/zombies/{id} : Mettre à jour un zombie
DELETE /api/zombies/{id} : Supprimer un zombie

### Plantes

GET /api/plantes : Récupérer toutes les plantes
GET /api/plantes/{id} : Récupérer une plante par son ID
GET /api/plantes/effet/{effet} : Récupérer les plantes par effet
POST /api/plantes : Créer une plante
PUT /api/plantes/{id} : Mettre à jour une plante
DELETE /api/plantes/{id} : Supprimer une plante

### Maps

GET /api/maps : Récupérer toutes les cartes
GET /api/maps/{id} : Récupérer une carte par son ID
POST /api/maps : Créer une carte
PUT /api/maps/{id} : Mettre à jour une carte
DELETE /api/maps/{id} : Supprimer une carte

## Modèles de données
### Zombie
{
  "id": 1,
  "nom": "Zombie de base",
  "pointDeVie": 100,
  "attaqueParSeconde": 0.8,
  "degatAttaque": 10,
  "vitesseDeDeplacement": 0.5,
  "cheminImage": "images/zombie/zombie.png",
  "mapId": 1
}

### Plante
{
  "id": 1,
  "nom": "Tournesol",
  "pointDeVie": 100,
  "attaqueParSeconde": 0.0,
  "degatAttaque": 0,
  "cout": 50,
  "soleilParSeconde": 25.0,
  "effet": "normal",
  "cheminImage": "images/plante/tournesol.png"
}

### Map
{
  "id": 1,
  "ligne": 5,
  "colonne": 9,
  "cheminImage": "images/map/gazon.png"
}

## Auteur

Ivan Cocusse

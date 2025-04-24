# Plants vs Zombies - API Backend
Ce projet est une implémentation backend d'une API REST pour un jeu inspiré de Plants vs Zombies, permettant la gestion des zombies, des plantes et des cartes via des opérations CRUD.
## Technologies utilisées

Langage: Java 21<br />
Framework: Spring MVC<br />
Base de données: MySQL<br />
Outil d'accès aux données: JdbcTemplate<br />
Serveur d'application: Tomcat 10<br />
Gestion des dépendances: Maven<br />

## Structure du projet
Le projet suit une architecture en couches :<br />

Modèles (core/model/) : Représentation des entités de la base de données<br />
DAOs (repository/) : Accès aux données via JdbcTemplate<br />
Services (core/Service/) : Logique métier<br />
DTOs (API/DTO/) : Objets de transfert de données pour l'API<br />
Contrôleurs (API/controller/) : Points d'entrée REST<br />

## Configuration
Le projet est configuré pour utiliser une base de données MySQL locale. Les paramètres de connexion se trouvent dans la classe DatabaseConfig.
## Installation et déploiement

Cloner le repository<br />
Configurer une base de données MySQL avec les scripts fournis (init.sql et values.sql)<br />
Configurer les paramètres de connexion dans DatabaseConfig.java<br />
Compiler le projet avec Maven : mvn clean package<br />
Déployer le WAR généré sur Tomcat<br />

## Endpoints API
### Zombies

GET /api/zombies : Récupérer tous les zombies<br />
GET /api/zombies/{id} : Récupérer un zombie par son ID<br />
GET /api/zombies/map/{mapId} : Récupérer les zombies par carte<br />
POST /api/zombies : Créer un zombie<br />
PUT /api/zombies/{id} : Mettre à jour un zombie<br />
DELETE /api/zombies/{id} : Supprimer un zombie<br />

### Plantes

GET /api/plantes : Récupérer toutes les plantes<br />
GET /api/plantes/{id} : Récupérer une plante par son ID<br />
GET /api/plantes/effet/{effet} : Récupérer les plantes par effet<br />
POST /api/plantes : Créer une plante<br />
PUT /api/plantes/{id} : Mettre à jour une plante<br />
DELETE /api/plantes/{id} : Supprimer une plante<br />

### Maps

GET /api/maps : Récupérer toutes les cartes<br />
GET /api/maps/{id} : Récupérer une carte par son ID<br />
POST /api/maps : Créer une carte<br />
PUT /api/maps/{id} : Mettre à jour une carte<br />
DELETE /api/maps/{id} : Supprimer une carte<br />

## Modèles de données
### Zombie
{<br />
  "id_zombie": 1,<br />
  "nom": "Zombie de base",<br />
  "point_de_vie": 100,<br />
  "attaque_par_seconde": 0.8,<br />
  "degat_attaque": 10,<br />
  "vitesse_de_deplacement": 0.5,<br />
  "chemin_image": "images/zombie/zombie.png",<br />
  "id_map": 1<br />
}

### Plante
{<br />
  "id_plante": 1,<br />
  "nom": "Tournesol",<br />
  "point_de_vie": 100,<br />
  "attaque_par_seconde": 0.0,<br />
  "degat_dttaque": 0,<br />
  "cout": 50,<br />
  "soleil_par_seconde": 25.0,<br />
  "effet": "normal",<br />
  "chemin_image": "images/plante/tournesol.png"<br />
}<br />

### Map
{<br />
  "id_map": 1,<br />
  "ligne": 5,<br />
  "colonne": 9,<br />
  "chemin_image": "images/map/gazon.png"<br />
}<br />

## Auteur

Ivan Cocusse

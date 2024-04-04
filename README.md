# Bienvenue sur Châtop
Châtop est un portail de mise en relation locataires-propriétaires pour la location saisonnière.
Les propriétaires peuvent publier des annonces de location et les locataires peuvent rechercher des annonces qui correspondent à leurs critères.
De plus, ils peuvent contacter les propriétaires des annonces qui les intéressent pour obtenir plus d'informations.

## Technologies utilisées
- Backend
- Spring Boot (Java)
- Frontend: Angular
- Base de données: MySQL
- Documentation de l'API: Swagger

## Fonctionnalités
### Authentification 
Les utilisateurs doivent s'authentifier pour accéder aux annonces de location. 
### Recherche d'annonces
Les utilisateurs peuvent rechercher des annonces de location en fonction de différents critères (lieu, prix, description, surface, etc.).
### Contact propriétaire
Les utilisateurs peuvent contacter les propriétaires des annonces qui les intéressent.
### Gestion des utilisateurs
Les utilisateurs peuvent créer un compte, se connecter et gérer leur profil.
### Gestion des annonces
Les propriétaires peuvent ajouter, modifier et supprimer leurs annonces de location.

## Comment initialiser le projet sur votre machine
- Clonez le repository depuis GitHub.
- Depuis le dossier ```frontend```, installez les dépendances avec ```npm install```.
- Depuis le dossier ```frontend```, démarrez le serveur Angular avec ```ng serve```.
- Créez une base de données MySQL et importez le schéma fourni dans le fichier  ```/api/src/main/ressources/data.sql```.
- Installez maven sur votre machine.
- Depuis le dossier ```api```, démarrez le serveur Spring Boot avec ```mvn spring-boot:run```.
- Accédez à l'API via ```http://localhost:9192/swagger-ui.html```.
- Accédez à l'application Angular via ```http://localhost:4200```.

## Documentation de l'API
La documentation de l'API est disponible via Swagger. Une fois le serveur démarré, accédez à ```http://localhost:9192/api-docs``` pour voir la documentation et tester les différentes routes de l'API.

## Collection Postman
Une collection Postman est fournie dans le repository pour tester l'API. Importez cette collection dans votre client Postman pour faciliter les tests et l'exploration des fonctionnalités de l'API.

## Schéma de base de données
Le schéma de base de données est disponible dans le repository. Utilisez ce schéma pour créer la structure de la base de données MySQL nécessaire au fonctionnement de l'application.

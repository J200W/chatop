# README.md
Portail de mise en relation locataires-propriétaires pour la location saisonnière
Ce projet est un portail en ligne permettant aux utilisateurs de rechercher et de contacter les propriétaires de propriétés disponibles à la location saisonnière sur la côte basque, et éventuellement dans toute la France. Les utilisateurs doivent s'authentifier pour accéder aux annonces de location.

## Technologies utilisées
- Backend: Spring Boot
- Base de données: MySQL ```(dans le fichier /api/min/ressources/data.txt)```
- Documentation de l'API: Swagger
- Outils supplémentaires: Postman, Mockoon

## Fonctionnalités
- Authentification: Les utilisateurs doivent s'authentifier pour accéder aux annonces de location.
- Recherche d'annonces: Les utilisateurs peuvent rechercher des annonces de location en fonction de différents critères (lieu, prix, nombre de chambres, etc.).
- Contact propriétaire: Les utilisateurs peuvent contacter les propriétaires des annonces qui les intéressent.
- Gestion des utilisateurs: Les utilisateurs peuvent créer un compte, se connecter et gérer leur profil.
- Gestion des annonces: Les propriétaires peuvent ajouter, modifier et supprimer leurs annonces de location.

## Comment utiliser le projet
- Cloner le repository depuis GitHub.
- Installer les dépendances avec ```npm install```.
- Créer une base de données PostgreSQL et configurer les paramètres de connexion dans le fichier .env.
- Exécuter les migrations de la base de données avec ```npm run migrate```.
- Démarrer le serveur avec ```npm start```.
- Accéder à l'API via ```http://localhost:9192```.

## Documentation de l'API
La documentation de l'API est disponible via Swagger. Une fois le serveur démarré, accédez à ```http://localhost:9192/api-docs``` pour voir la documentation et tester les différentes routes de l'API.

## Collection Postman
Une collection Postman est fournie dans le repository pour tester l'API. Importez cette collection dans votre client Postman pour faciliter les tests et l'exploration des fonctionnalités de l'API.

## Schéma de base de données
Le schéma de base de données est disponible dans le repository. Utilisez ce schéma pour créer la structure de la base de données MySQL nécessaire au fonctionnement de l'application.

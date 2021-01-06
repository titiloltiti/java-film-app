# Projet Java ENSTA 2020-2021
## Plateformes logicielles - Quentin Biharé

### Contexte

Ce projet correspond au développement d'une application composée d'un back Spring-Boot (qui expose une Base de Donnée MySQL) et d'un front Angular.

### Déploiement

Dpuis la racine du projet :

``` cd myfilmlist && mvn spring-boot:run```

Dans un autre terminal, depuis la racine :

``` cd frontFilmList && npm start```

Ouvrir un navigateur à l'url ```http://localhost:4200/``` 
### Travail effectué 

- [x]  Mise en place de la base de données et des requêtes delete, getAll, create, getById, update du controller pour les films
- [x] Ajout d'une table Réalisateur dans la BD et des requpetes associées (lien avec les films également) 
- [x] Pagination pour les films
- [] Pagination pour les réalisateurs
- [x] Front Angular correspondant au back, avec formulaires validés et dashboard
- [x] Filtre sur le nom du film, du réalisateur pour les films
- [x] Order sur l'id, le nom, la durée d'un film (côté client et imparfait)
- [] Ajout des Genres
- [] Mise en place de Sonar
- [x] Traduction
- [] Tests
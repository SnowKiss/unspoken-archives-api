# Unspoken Archives

## Présentation du Projet

"Unspoken Archives" est une application web conçue pour les amateurs de jeux de rôle (JDR) et les développeurs de jeux. Elle permet la génération automatique de profils de Personnages Non Joueurs (PNJ) avec des détails enrichis, y compris des noms, des backgrounds, des occupations, et plus encore, en utilisant l'intelligence artificielle.

Cette application est idéale pour les maîtres de jeu (MJ) qui ont besoin de créer rapidement des PNJ détaillés pour leurs campagnes de JDR, ou pour les développeurs de jeux cherchant à peupler leurs mondes de jeu avec des personnages uniques.

## Technologies Utilisées

- Java 21.0.1
- Maven 3.9.5
- Quarkus 3.4

## Lancer l'application en mode développement

Vous pouvez exécuter votre application en mode développement qui permet le live coding avec la commande suivante :
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE :_** Quarkus est maintenant livré avec une interface Dev UI, qui est disponible en mode développement uniquement à l'adresse http://localhost:8080/q/dev/.

## Packaging et exécution de l'application

L'application peut être empaquetée en utilisant :
```shell script
./mvnw package
```
Cela produit le fichier `quarkus-run.jar` dans le répertoire `target/quarkus-app/`.
Notez que ce n’est pas un _über-jar_ car les dépendances sont copiées dans le répertoire `target/quarkus-app/lib/`.

L'application est maintenant exécutable en utilisant `java -jar target/quarkus-app/quarkus-run.jar`.

Si vous souhaitez construire un _über-jar_, exécutez la commande suivante :
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

L'application, empaquetée comme un _über-jar_, est maintenant exécutable en utilisant `java -jar target/*-runner.jar`.

## Créer un exécutable natif

Vous pouvez créer un exécutable natif en utilisant :
```shell script
./mvnw package -Dnative
```

Ou, si vous n'avez pas GraalVM installé, vous pouvez exécuter la construction de l'exécutable natif dans un conteneur en utilisant :
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Vous pouvez ensuite exécuter votre exécutable natif avec : `./target/unspoken-archives-api-1.0.0-SNAPSHOT-runner`

Si vous souhaitez en savoir plus sur la création d'exécutables natifs, veuillez consulter https://quarkus.io/guides/maven-tooling.

## Guides Associés

- Ressources REST pour Hibernate ORM avec Panache ([guide](https://quarkus.io/guides/rest-data-panache)) : Générez des ressources REST Jakarta pour vos entités et dépôts Hibernate Panache
- Client REST Réactif ([guide](https://quarkus.io/guides/rest-client-reactive)) : Appelez des services REST de manière réactive
- RESTEasy Réactif ([guide](https://quarkus.io/guides/resteasy-reactive)) : Une implémentation REST Jakarta utilisant le traitement au moment de la construction et Vert.x. Cette extension n'est pas compatible avec l'extension quarkus-resteasy, ni avec aucune des extensions qui en dépendent.
- Pilote JDBC - PostgreSQL ([guide](https://quarkus.io/guides/datasource)) : Connectez-vous à la base de données PostgreSQL via JDBC

## Code Fourni

### RESTEasy Réactif

Commencez facilement vos services Web RESTful réactifs

[Section du guide associée...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

# Introduction
Le but de cette application est de s'affranchir des fiches papiers lors de parties de minigolf.  
Dans un second temps l'application sera capable de garder une trace des différentes parties de minigolf effectuées afin de ressortir des statistiques.
# Description technique
* L'application sera écrite en Kotlin.
* Elle utilisera les fonctions GPS et Internet.
# Fonctionnalités
## Principales
* L'application sera capable de détecter sur quelle minigolf l'utilisateur se trouve grâce au GPS.
* Un smartphone master pourra créer une partie et les autres smartphones pourront se connecter à la partie via internet.
* Les smartphones connectés pourront inscrire leurs scores et l'affichage sera actualisé sur les autres smartphones.
## Secondaires
* Une fois la partie terminée, les données sont ajoutées à une base de données externes.
* Des statistiques pourront être affichées grâce à la base de donnée.
## Maquette
[Maquette.pdf](files/minigolf-model.pdf "Maquette")
## Répartition des tâches
David Rihs : Création des vues XML et des interactions entre elles (Activity, Fragment)  
Sol Rosca : Gestion du MVVM  
Nathan Latino : Gestion des requêtes HTTP avec Fuel

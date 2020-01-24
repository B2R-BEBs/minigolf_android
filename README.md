# Description
Le but de cette application est de s'affranchir des fiches papiers lors de parties de minigolf.
Dans un second temps l'application sera capable de garder une trace des différentes parties de minigolf effectuées afin de ressortir des statistiques.

Site web [IGolf](https://swiped.srvz-webapp.he-arc.ch/)
dépôt du site web https://github.com/HE-Arc/Minigolf

# Manuel utilisateur
## Prérequis
L'application a besoin d'avoir la permission d'utiliser la localisation pour pouvoir donner la position la plus proche d'un minigolf.

![](https://i.imgur.com/gS5jgEd.png)

## Utilisation

Au lancement, l'application demande une authentification à l'utilisateur. L'email et le mot de passe doivent être entrés. Si l'utilisateur n'a pas de compte, un lien "Pas encore de compte ?" le revoie sur la page web de IGolf où il peut en faire un (Désactivé sur le site web).

**Comptes test:**
* user: `user@test.com` pass: `toptal00`
* user: `nath@test.com` pass: `toptal00`
* user: `sol@test.com` pass: `toptal00`
* user: `david@test.com` pass: `toptal00`
* user: `admin@test.com` pass: `toptal00`


![](https://i.imgur.com/BUg08jj.png)

Après s'être authentifié, l'utilisateur arrive sur la liste de toutes ses parties.

![](https://i.imgur.com/qZMbZVD.png)

Il peut sélectionner une partie pour avoir un récapitulatif et peut revenir à la liste en appuyant sur le bouton retour.
![](https://i.imgur.com/a0dXCmA.png)

En allant dans l'onglet "graphique", il peut visionné les statistiques de ses parties. chaque barre correspond à une partie et le nombre de coups joués.

![](https://i.imgur.com/PykuiKL.png)

Dans l'onglet "liste" un bouton "Commencer une nouvelle partie" permet soit de rejoindre une partie en donnant un code servant à identifier une partie soit de créer une nouvelle partie.

![](https://i.imgur.com/HrqT201.png)

Pour la création d'une partie, l'application propose tous les minigolfs disponible et trie ces derniers en fonction de leur proximité avec la position actuelle de l'utilisateur. Une carte affiche le minigolf le plus proche ainsi que la position de l'utilisateur. 

![](https://i.imgur.com/D0pNQXX.png)

Après selection du minigolf, si il possède plusieurs courses, l'application affiche leurs noms, description ainsi que le nombre de trous de chaque courses. S'il n'existe qu'une seule course, l'application crée directement la partie.

![](https://i.imgur.com/TB8j4FU.png)

Après la création de la partie, un code s'affiche. Ce code est donné aux autres joueurs de la partie pour qu'ils puissent la rejoindre.

Dans l'onglet "mon score", l'utilisateur peut ajouter le résultat qu'il fait à chaque trou. Quand l'utilisateur a joué tous les trous, le bouton "fin de la partie" s'active et il peut mettre fin à la partie.

![](https://i.imgur.com/VdkbcFr.png)

À tout moment pendant la partie, dans l'onglet "résultats", il peut voir un résumé de tous les scores de toutes les personnes qui jouent dans la même partie ainsi que la somme des points. La mise à jour des scores est automatique et s'effectue en temps réel pour tous les joueurs sans nécessiter de rechargement de la page. De même, il est possible de consulter l'évolution d'une partie sur le [site web](https://swiped.srvz-webapp.he-arc.ch/) sur la page "my scores" d'un membre de la partie (nécessite d'être authentifié).

![](https://i.imgur.com/1i0uoRQ.png)

Toute les parties sont enregistrées et sont synchronisées avec le serveur web. Toutes les données d'un utilisateur sont consultables à partir du [site web](https://swiped.srvz-webapp.he-arc.ch/) (nécessite d'être authentifié avec les mêmes credential que ceux de l'application pour accéder aux résumés des parties. Seul les minigolfs et leurs détails sont en consultation libre).

![](https://i.imgur.com/XIJDsQN.png)

### Installation
Pour l'installation de l'application, cloner le dépôt git https://github.com/rihsdavid/minigolf_android.
Lancer l'application depuis Android Studio.

Possibilité de lancer en émulateur et smartphone en même temps pour tester une partie à plusieurs.

Aucun APK n'est actuellement disponible


# Bugs connu

* Retour en arrière pendant qu'une partie est active suivi d'une nouvelle création de partie fait crash l'application.
* Si deux (ou plus) joueurs se connectent au même moment l'application de l'hôte crash.
* Parfois si un utilisateur connecté change de compte, Les données du nouvel utilisateur ne sont pas mises à jour.
# **CoreLittle V1.2 (1.21.4)**

CoreLittle est un plugin léger pour Minecraft qui vise à enrichir l'expérience de jeu avec des commandes simples et utiles pour les administrateurs, les joueurs, les VIP, les Créateurs de contenus, les builders, etc...

### Fonctionnalités principales :
Gestion de commandes administratives et utilitaires pour les joueurs.
Messages personnalisables pour une meilleure communication sur le serveur.
Permissions intégrées pour une gestion fine des accès.

### Nouveauté de CoreLittle **V1.2** :
Ajout des Ranks (Personnalisable), D'un TabList (Personnalisable) et d'une commande pour les Administrateurs, Le /staff. Une interface moderne pour administrer un serveur ! (encore en développement)

Le Main et les Commandes sont dans :

- src/main/java/fr/itmozlegends/CoreLittle/Commands (les commandes)
- src/main/java/fr/itmozlegends/CoreLittle/CoreLittle.java (le main)

Pour personnaliser les Ranks et Le TabList : 

<fichiers_du_serveur> -> Plugins -> CoreLittle -> rank.yml et TabList.yml

Liste des commandes de CoreLittle **V1.2** :
**/setrank** : Permet de changer le rank d'un joueur.

Liste des commandes de **CoreLittle** :

🔧 Commandes **Administratives** :
**/stop** : Permet de stopper le serveur (un cooldown de 10 secondes sera effectué pour dire aux joueurs que le serveur s'éteindra)
**/ban** : Permet de bannir un joueur.
**/banoffline** : Permet de bannir un joueur qui n'est pas connecté sur le serveur.
**/pardon et /unban** : Permet de débannir un joueur.
**/op** : Permet de mettre opérateur un joueur.
**/deop** : Permet d'enlever les permitions d'administrateur d'un administrateur.
**/gm** : Changer de mode de jeu.
**/broadcast** <_message_> : Envoie un message visible par tous les joueurs.
**/freeze** : Gèle ou dégèle un joueur.
**/vanish** : Permet de devenir invisible ou visible.
**/spawn-pet** : Permet de faire spawn un animal.
**/pet** : Permet de se transformer en un animal (seulement le chien pour l'instant)

🧱 Commandes pour les **Builders** :
**/set** : Définit une région avec un matériau. (Des Petits Bugs)
**/cut** : Permet d'enlever des blocs dans une région.
**/undo** : Permet de revenir en arrière.

💎 Commandes Utilitaires pour les **VIP** :
**/hat** : Permet de mettre un bloc sur sa tête.
**/skin** : Permet de changer de skin.

🎥 Commandes Utilitaires pour les **créateurs de contenus** (_Streameur_):
**/streammode** : Permet de se mettre en mode Streameur (plus avoir de bruit dérangeant et ne pas voir de lien dans le chat (en développement))

🧭 Commandes Utilitaires pour **Joueurs** :
**/help** <_admin / staff / vip / streamer / (ou rien)_> : Permet toutes les commandes du serveur.

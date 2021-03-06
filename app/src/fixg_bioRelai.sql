-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Ven 12 Avril 2019 à 15:27
-- Version du serveur :  10.1.26-MariaDB-0+deb9u1
-- Version de PHP :  7.0.19-1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `fixg_bioRelai`
--

-- --------------------------------------------------------

--
-- Structure de la table `ADHERENT`
--

CREATE TABLE `ADHERENT` (
  `IDADHERENT` int(11) NOT NULL,
  `IDUTILISATEUR` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `ADHERENT`
--

INSERT INTO `ADHERENT` (`IDADHERENT`, `IDUTILISATEUR`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `CATEGORIE`
--

CREATE TABLE `CATEGORIE` (
  `IDCATEGORIE` int(11) NOT NULL,
  `NOMCATEGORIE` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `CATEGORIE`
--

INSERT INTO `CATEGORIE` (`IDCATEGORIE`, `NOMCATEGORIE`) VALUES
(1, 'Fruits');

-- --------------------------------------------------------

--
-- Structure de la table `COMMANDE`
--

CREATE TABLE `COMMANDE` (
  `IDCOMMANDE` int(11) NOT NULL,
  `IDADHERENT` int(11) NOT NULL,
  `DATECOMMANDE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `COMMANDE`
--

INSERT INTO `COMMANDE` (`IDCOMMANDE`, `IDADHERENT`, `DATECOMMANDE`) VALUES
(1, 1, '2019-04-05'),
(2, 1, '2019-04-06'),
(3, 1, '2019-04-06'),
(4, 1, '2019-04-06'),
(5, 1, '2019-04-12');

-- --------------------------------------------------------

--
-- Structure de la table `LIGNES_COMMANDE`
--

CREATE TABLE `LIGNES_COMMANDE` (
  `IDPRODUIT` int(11) NOT NULL,
  `IDCOMMANDE` int(11) NOT NULL,
  `QUANTITE` decimal(10,2) DEFAULT NULL,
  `QUANTITELIVREECLIENT` decimal(10,2) DEFAULT NULL,
  `QUANTITERECUPERECLIENT` decimal(10,2) DEFAULT NULL,
  `QUANTITELIVREEPRODUCTEUR` decimal(10,2) DEFAULT NULL,
  `QUANTITERECUPEREPRODUCTEUR` decimal(10,2) DEFAULT NULL,
  `VUERESPONSABLE` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `LIGNES_COMMANDE`
--

INSERT INTO `LIGNES_COMMANDE` (`IDPRODUIT`, `IDCOMMANDE`, `QUANTITE`, `QUANTITELIVREECLIENT`, `QUANTITERECUPERECLIENT`, `QUANTITELIVREEPRODUCTEUR`, `QUANTITERECUPEREPRODUCTEUR`, `VUERESPONSABLE`) VALUES
(1, 1, '5.00', '5.00', '1.00', '2.00', '5.00', 1),
(2, 1, '5.00', '5.00', '1.00', '2.00', '5.00', 1),
(3, 1, '5.00', '5.00', '1.00', '2.00', '5.00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `PRODUCTEUR`
--

CREATE TABLE `PRODUCTEUR` (
  `IDPRODUCTEUR` int(11) NOT NULL,
  `IDUTILISATEUR` int(11) NOT NULL,
  `ADRESSEPRODUCTEUR` varchar(50) DEFAULT NULL,
  `COMMUNEPRODUCTEUR` varchar(40) DEFAULT NULL,
  `CODEPOSTALPRODUCTEUR` varchar(5) DEFAULT NULL,
  `DESCRIPTIFPRODUCTEUR` varchar(255) DEFAULT NULL,
  `PHOTOPRODUCTEUR` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `PRODUCTEUR`
--

INSERT INTO `PRODUCTEUR` (`IDPRODUCTEUR`, `IDUTILISATEUR`, `ADRESSEPRODUCTEUR`, `COMMUNEPRODUCTEUR`, `CODEPOSTALPRODUCTEUR`, `DESCRIPTIFPRODUCTEUR`, `PHOTOPRODUCTEUR`) VALUES
(1, 2, '42 rue sauteyron', 'Bordeaux', '33000', 'voici un descriptif', 'voici ma photo');

-- --------------------------------------------------------

--
-- Structure de la table `PRODUIT`
--

CREATE TABLE `PRODUIT` (
  `IDPRODUIT` int(11) NOT NULL,
  `IDPRODUCTEUR` int(11) DEFAULT NULL,
  `IDCATEGORIE` int(11) NOT NULL,
  `NOMPRODUIT` varchar(50) DEFAULT NULL,
  `DESCRIPTIFPRODUIT` varchar(255) DEFAULT NULL,
  `PHOTOPRODUIT` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `PRODUIT`
--

INSERT INTO `PRODUIT` (`IDPRODUIT`, `IDPRODUCTEUR`, `IDCATEGORIE`, `NOMPRODUIT`, `DESCRIPTIFPRODUIT`, `PHOTOPRODUIT`) VALUES
(1, 1, 1, 'Pomme', 'uneDescription', 'unePhoto'),
(2, 1, 1, 'Poire', 'uneDescription', 'unePhoto'),
(3, 1, 1, 'Banane', 'uneDescription', 'unePhoto');

-- --------------------------------------------------------

--
-- Structure de la table `PROPOSER`
--

CREATE TABLE `PROPOSER` (
  `IDPRODUIT` int(11) NOT NULL,
  `IDVENTE` varchar(5) NOT NULL,
  `UNITE` varchar(10) DEFAULT NULL,
  `QUANTITE` varchar(5) DEFAULT NULL,
  `PRIX` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `UTILISATEUR`
--

CREATE TABLE `UTILISATEUR` (
  `IDUTILISATEUR` int(11) NOT NULL,
  `MAIL` varchar(50) DEFAULT NULL,
  `MDP` varchar(128) DEFAULT NULL,
  `STATUT` varchar(15) DEFAULT NULL,
  `NOMUTILISATEUR` char(40) DEFAULT NULL,
  `PRENOMUTILISATEUR` varchar(40) DEFAULT NULL,
  `LOGIN` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `UTILISATEUR`
--

INSERT INTO `UTILISATEUR` (`IDUTILISATEUR`, `MAIL`, `MDP`, `STATUT`, `NOMUTILISATEUR`, `PRENOMUTILISATEUR`, `LOGIN`) VALUES
(1, 'clement.guestereguy@gmail.com', 'clement', 'client', 'GUESTEREGUY', 'Clement', 'cguestereguy'),
(2, 'thoogui@gmail.com', 'thoogui', 'responsable', 'FIX', 'Guillaume', 'thoogui'),
(3, 'maxime.maitre@gmail.com', 'maxime', 'producteur', 'MAITRE', 'Maxime', 'mmaitre');

-- --------------------------------------------------------

--
-- Structure de la table `VENTE`
--

CREATE TABLE `VENTE` (
  `IDVENTE` varchar(5) NOT NULL,
  `DATEVENTE` date DEFAULT NULL,
  `DATEDEBUTPROD` date DEFAULT NULL,
  `DATEFINPROD` date DEFAULT NULL,
  `DATEDEBUTCLI` date DEFAULT NULL,
  `DATEFINCLI` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ADHERENT`
--
ALTER TABLE `ADHERENT`
  ADD PRIMARY KEY (`IDADHERENT`),
  ADD KEY `I_FK_ADHERENT_UTILISATEUR` (`IDUTILISATEUR`);

--
-- Index pour la table `CATEGORIE`
--
ALTER TABLE `CATEGORIE`
  ADD PRIMARY KEY (`IDCATEGORIE`);

--
-- Index pour la table `COMMANDE`
--
ALTER TABLE `COMMANDE`
  ADD PRIMARY KEY (`IDCOMMANDE`),
  ADD KEY `I_FK_COMMANDE_ADHERENT` (`IDADHERENT`);

--
-- Index pour la table `LIGNES_COMMANDE`
--
ALTER TABLE `LIGNES_COMMANDE`
  ADD PRIMARY KEY (`IDPRODUIT`,`IDCOMMANDE`),
  ADD KEY `I_FK_LIGNES_COMMANDE_PRODUIT` (`IDPRODUIT`),
  ADD KEY `I_FK_LIGNES_COMMANDE_COMMANDE` (`IDCOMMANDE`);

--
-- Index pour la table `PRODUCTEUR`
--
ALTER TABLE `PRODUCTEUR`
  ADD PRIMARY KEY (`IDPRODUCTEUR`),
  ADD KEY `I_FK_PRODUCTEUR_UTILISATEUR` (`IDUTILISATEUR`);

--
-- Index pour la table `PRODUIT`
--
ALTER TABLE `PRODUIT`
  ADD PRIMARY KEY (`IDPRODUIT`),
  ADD KEY `I_FK_PRODUIT_PRODUCTEUR` (`IDPRODUCTEUR`),
  ADD KEY `I_FK_PRODUIT_CATEGORIE` (`IDCATEGORIE`);

--
-- Index pour la table `PROPOSER`
--
ALTER TABLE `PROPOSER`
  ADD PRIMARY KEY (`IDPRODUIT`,`IDVENTE`),
  ADD KEY `I_FK_PROPOSER_PRODUIT` (`IDPRODUIT`),
  ADD KEY `I_FK_PROPOSER_VENTE` (`IDVENTE`);

--
-- Index pour la table `UTILISATEUR`
--
ALTER TABLE `UTILISATEUR`
  ADD PRIMARY KEY (`IDUTILISATEUR`);

--
-- Index pour la table `VENTE`
--
ALTER TABLE `VENTE`
  ADD PRIMARY KEY (`IDVENTE`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `ADHERENT`
--
ALTER TABLE `ADHERENT`
  MODIFY `IDADHERENT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `CATEGORIE`
--
ALTER TABLE `CATEGORIE`
  MODIFY `IDCATEGORIE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `COMMANDE`
--
ALTER TABLE `COMMANDE`
  MODIFY `IDCOMMANDE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `PRODUCTEUR`
--
ALTER TABLE `PRODUCTEUR`
  MODIFY `IDPRODUCTEUR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `PRODUIT`
--
ALTER TABLE `PRODUIT`
  MODIFY `IDPRODUIT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `UTILISATEUR`
--
ALTER TABLE `UTILISATEUR`
  MODIFY `IDUTILISATEUR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `ADHERENT`
--
ALTER TABLE `ADHERENT`
  ADD CONSTRAINT `FK_ADHERENT_UTILISATEUR` FOREIGN KEY (`IDUTILISATEUR`) REFERENCES `UTILISATEUR` (`IDUTILISATEUR`);

--
-- Contraintes pour la table `COMMANDE`
--
ALTER TABLE `COMMANDE`
  ADD CONSTRAINT `FK_COMMANDE_ADHERENT` FOREIGN KEY (`IDADHERENT`) REFERENCES `ADHERENT` (`IDADHERENT`);

--
-- Contraintes pour la table `LIGNES_COMMANDE`
--
ALTER TABLE `LIGNES_COMMANDE`
  ADD CONSTRAINT `FK_LIGNES_COMMANDE_COMMANDE` FOREIGN KEY (`IDCOMMANDE`) REFERENCES `COMMANDE` (`IDCOMMANDE`),
  ADD CONSTRAINT `FK_LIGNES_COMMANDE_PRODUIT` FOREIGN KEY (`IDPRODUIT`) REFERENCES `PRODUIT` (`IDPRODUIT`);

--
-- Contraintes pour la table `PRODUCTEUR`
--
ALTER TABLE `PRODUCTEUR`
  ADD CONSTRAINT `FK_PRODUCTEUR_UTILISATEUR` FOREIGN KEY (`IDUTILISATEUR`) REFERENCES `UTILISATEUR` (`IDUTILISATEUR`);

--
-- Contraintes pour la table `PRODUIT`
--
ALTER TABLE `PRODUIT`
  ADD CONSTRAINT `FK_PRODUIT_CATEGORIE` FOREIGN KEY (`IDCATEGORIE`) REFERENCES `CATEGORIE` (`IDCATEGORIE`),
  ADD CONSTRAINT `FK_PRODUIT_PRODUCTEUR` FOREIGN KEY (`IDPRODUCTEUR`) REFERENCES `PRODUCTEUR` (`IDPRODUCTEUR`);

--
-- Contraintes pour la table `PROPOSER`
--
ALTER TABLE `PROPOSER`
  ADD CONSTRAINT `FK_PROPOSER_PRODUIT` FOREIGN KEY (`IDPRODUIT`) REFERENCES `PRODUIT` (`IDPRODUIT`),
  ADD CONSTRAINT `FK_PROPOSER_VENTE` FOREIGN KEY (`IDVENTE`) REFERENCES `VENTE` (`IDVENTE`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

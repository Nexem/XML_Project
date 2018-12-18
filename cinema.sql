-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 18 déc. 2018 à 14:42
-- Version du serveur :  5.7.24-log
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `cinema`
--

-- --------------------------------------------------------

--
-- Structure de la table `actor`
--
create database cinema;
use cinema;

DROP TABLE IF EXISTS `actor`;
CREATE TABLE IF NOT EXISTS `actor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `actor`
--

INSERT INTO `actor` (`id`, `name`) VALUES
(1, 'jack'),
(2, 'pascal'),
(5, 'Baptiste'),
(6, 'Francais'),
(9, 'damien'),
(10, 'Leo');

-- --------------------------------------------------------

--
-- Structure de la table `actor_movie`
--

DROP TABLE IF EXISTS `actor_movie`;
CREATE TABLE IF NOT EXISTS `actor_movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_actor` int(11) NOT NULL,
  `id_movie` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_actor` (`id_actor`),
  KEY `id_movie` (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `actor_movie`
--

INSERT INTO `actor_movie` (`id`, `id_actor`, `id_movie`) VALUES
(19, 2, 32),
(20, 2, 32),
(21, 5, 31),
(22, 2, 31),
(24, 1, 32),
(25, 1, 31);

-- --------------------------------------------------------

--
-- Structure de la table `adress`
--

DROP TABLE IF EXISTS `adress`;
CREATE TABLE IF NOT EXISTS `adress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `street` text NOT NULL,
  `city` text NOT NULL,
  `zip` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `cinema`
--

DROP TABLE IF EXISTS `cinema`;
CREATE TABLE IF NOT EXISTS `cinema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_city` int(11) NOT NULL,
  `name` text NOT NULL,
  `id_address` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_address` (`id_address`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `city`
--

DROP TABLE IF EXISTS `city`;
CREATE TABLE IF NOT EXISTS `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `city`
--

INSERT INTO `city` (`id`, `name`) VALUES
(1, 'St-hilarion'),
(2, 'Paris'),
(3, 'Rambouillet');

-- --------------------------------------------------------

--
-- Structure de la table `day_cinema`
--

DROP TABLE IF EXISTS `day_cinema`;
CREATE TABLE IF NOT EXISTS `day_cinema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` text NOT NULL,
  `id_movie_show` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_movie_show` (`id_movie_show`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `director`
--

DROP TABLE IF EXISTS `director`;
CREATE TABLE IF NOT EXISTS `director` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `director`
--

INSERT INTO `director` (`id`, `name`) VALUES
(1, 'french'),
(2, 'Bapt'),
(3, 'Damien');

-- --------------------------------------------------------

--
-- Structure de la table `director_movie`
--

DROP TABLE IF EXISTS `director_movie`;
CREATE TABLE IF NOT EXISTS `director_movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_director` int(11) NOT NULL,
  `id_movie` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_director` (`id_director`),
  KEY `id_movie` (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `director_movie`
--

INSERT INTO `director_movie` (`id`, `id_director`, `id_movie`) VALUES
(8, 2, 32),
(9, 2, 32),
(12, 3, 31);

-- --------------------------------------------------------

--
-- Structure de la table `language`
--

DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `language`
--

INSERT INTO `language` (`id`, `name`) VALUES
(1, 'Steven'),
(2, 'French'),
(3, 'English');

-- --------------------------------------------------------

--
-- Structure de la table `language_movie`
--

DROP TABLE IF EXISTS `language_movie`;
CREATE TABLE IF NOT EXISTS `language_movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_movie` int(11) NOT NULL,
  `id_language` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_movie` (`id_movie`),
  KEY `id_language` (`id_language`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `language_movie`
--

INSERT INTO `language_movie` (`id`, `id_movie`, `id_language`) VALUES
(4, 31, 2),
(5, 32, 3),
(12, 31, 3);

-- --------------------------------------------------------

--
-- Structure de la table `movie`
--

DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `duration` text NOT NULL,
  `min_age` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `movie`
--

INSERT INTO `movie` (`id`, `title`, `duration`, `min_age`) VALUES
(31, 'bapt', '1h45', '12'),
(32, 'sfe', '1h45', '48');

-- --------------------------------------------------------

--
-- Structure de la table `movie_show_cinema`
--

DROP TABLE IF EXISTS `movie_show_cinema`;
CREATE TABLE IF NOT EXISTS `movie_show_cinema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_city` int(11) NOT NULL,
  `id_movie` int(11) NOT NULL,
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_movie` (`id_movie`),
  KEY `id_city` (`id_city`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `movie_show_cinema`
--

INSERT INTO `movie_show_cinema` (`id`, `id_city`, `id_movie`, `start`, `end`) VALUES
(8, 1, 31, '2018-12-18 11:05:18', NULL),
(9, 1, 32, '2018-12-18 11:55:26', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`) VALUES
(1, 'bapt', '123'),
(3, 'Jack', '123');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `actor_movie`
--
ALTER TABLE `actor_movie`
  ADD CONSTRAINT `actor_movie_ibfk_1` FOREIGN KEY (`id_actor`) REFERENCES `actor` (`id`),
  ADD CONSTRAINT `actor_movie_ibfk_2` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id`);

--
-- Contraintes pour la table `cinema`
--
ALTER TABLE `cinema`
  ADD CONSTRAINT `cinema_ibfk_1` FOREIGN KEY (`id_address`) REFERENCES `cinema` (`id`),
  ADD CONSTRAINT `cinema_ibfk_2` FOREIGN KEY (`id_address`) REFERENCES `adress` (`id`);

--
-- Contraintes pour la table `day_cinema`
--
ALTER TABLE `day_cinema`
  ADD CONSTRAINT `day_cinema_ibfk_1` FOREIGN KEY (`id_movie_show`) REFERENCES `movie_show_cinema` (`id`);

--
-- Contraintes pour la table `director_movie`
--
ALTER TABLE `director_movie`
  ADD CONSTRAINT `director_movie_ibfk_1` FOREIGN KEY (`id_director`) REFERENCES `director` (`id`),
  ADD CONSTRAINT `director_movie_ibfk_3` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id`);

--
-- Contraintes pour la table `language_movie`
--
ALTER TABLE `language_movie`
  ADD CONSTRAINT `language_movie_ibfk_1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id`),
  ADD CONSTRAINT `language_movie_ibfk_2` FOREIGN KEY (`id_language`) REFERENCES `language` (`id`);

--
-- Contraintes pour la table `movie_show_cinema`
--
ALTER TABLE `movie_show_cinema`
  ADD CONSTRAINT `movie_show_cinema_ibfk_2` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id`),
  ADD CONSTRAINT `movie_show_cinema_ibfk_3` FOREIGN KEY (`id_city`) REFERENCES `city` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- MySQL Script generated by MySQL Workbench
-- Thu May  6 16:54:49 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema baseball
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `baseball` ;

-- -----------------------------------------------------
-- Schema baseball
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baseball` DEFAULT CHARACTER SET utf8 ;
USE `baseball` ;

-- -----------------------------------------------------
-- Table `baseball`.`team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`team` ;

CREATE TABLE IF NOT EXISTS `baseball`.`team` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`game` ;

CREATE TABLE IF NOT EXISTS `baseball`.`game` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `home_team_id` BIGINT NOT NULL,
    `away_team_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `team_name_idx` (`home_team_id` ASC),
    INDEX `game_away_team_id_fk_idx` (`away_team_id` ASC),
    CONSTRAINT `game_home_team_id_fk`
    FOREIGN KEY (`home_team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `game_away_team_id_fk`
    FOREIGN KEY (`away_team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`player` ;

CREATE TABLE IF NOT EXISTS `baseball`.`player` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `team_id` BIGINT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `position` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC),
    CONSTRAINT `player_team_id_fk`
    FOREIGN KEY (`team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`inning`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`inning` ;

CREATE TABLE IF NOT EXISTS `baseball`.`inning` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `game_id` BIGINT NOT NULL,
    `round` INT NOT NULL,
    `top_bottom` VARCHAR(45) NOT NULL DEFAULT 0,
    `out` INT NOT NULL DEFAULT 0,
    `strike` INT NOT NULL DEFAULT 0,
    `ball` INT NOT NULL DEFAULT 0,
    `base1` TINYINT(1) NOT NULL DEFAULT 0,
    `base2` TINYINT(1) NOT NULL DEFAULT 0,
    `base3` TINYINT(1) NOT NULL DEFAULT 0,
    `score` INT NOT NULL,
    `hit` TINYINT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    INDEX `game_id_fk_idx` (`game_id` ASC),
    CONSTRAINT `inning_game_id_fk`
    FOREIGN KEY (`game_id`)
    REFERENCES `baseball`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`offense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`offense` ;

CREATE TABLE IF NOT EXISTS `baseball`.`offense` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `team_id` BIGINT NOT NULL,
    `inning_id` BIGINT NOT NULL,
    `player_id` BIGINT NOT NULL,
    `at_bat` INT NULL DEFAULT 0,
    `hit` INT NULL DEFAULT 0,
    `order` INT,
    `on_turn` TINYINT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    INDEX `offense_inning_id_fk_idx` (`inning_id` ASC),
    INDEX `offense_team_id_fk_idx` (`team_id` ASC),
    INDEX `offense_player_id_fk_idx` (`player_id` ASC),
    CONSTRAINT `offense_inning_id_fk`
    FOREIGN KEY (`inning_id`)
    REFERENCES `baseball`.`inning` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `offense_team_id_fk`
    FOREIGN KEY (`team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `offense_player_id_fk`
    FOREIGN KEY (`player_id`)
    REFERENCES `baseball`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`defense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`defense` ;

CREATE TABLE IF NOT EXISTS `baseball`.`defense` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `team_id` BIGINT NOT NULL,
    `inning_id` BIGINT NOT NULL,
    `player_id` BIGINT NOT NULL,
    `pitch` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `defense_inning_id_idx` (`inning_id` ASC),
    INDEX `defense_team_id_fk_idx` (`team_id` ASC),
    INDEX `defense_player_id_fk_idx` (`player_id` ASC),
    CONSTRAINT `defense_inning_id_fk`
    FOREIGN KEY (`inning_id`)
    REFERENCES `baseball`.`inning` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `defense_team_id_fk`
    FOREIGN KEY (`team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `defense_player_id_fk`
    FOREIGN KEY (`player_id`)
    REFERENCES `baseball`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`user` ;

CREATE TABLE IF NOT EXISTS `baseball`.`user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(45) NOT NULL,
    `game_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_user_game1_idx` (`game_id` ASC),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC),
    CONSTRAINT `fk_user_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `baseball`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

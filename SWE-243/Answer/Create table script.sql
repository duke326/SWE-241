-- MySQL Script generated by MySQL Workbench
-- Sun Nov  1 14:23:31 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
USE `exercise5_1` ;

-- -----------------------------------------------------
-- Table `mydb`.`courses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exercise5_1`.`courses` ;

CREATE TABLE IF NOT EXISTS `exercise5_1`.`courses` (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(45) NULL,
  `day` VARCHAR(45) NULL,
  `start_time` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`courses_has_students`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exercise5_1`.`courses_has_students` ;

CREATE TABLE IF NOT EXISTS `exercise5_1`.`courses_has_students` (
  `courses_id` INT NOT NULL,
  `students_id` INT NOT NULL,
  PRIMARY KEY (`courses_id`, `students_id`),
  INDEX `fk_courses_has_students_students1_idx` (`students_id` ASC) VISIBLE,
  INDEX `fk_courses_has_students_courses_idx` (`courses_id` ASC) VISIBLE,
  CONSTRAINT `fk_courses_has_students_courses`
    FOREIGN KEY (`courses_id`)
    REFERENCES `exercise5_1`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_courses_has_students_students1`
    FOREIGN KEY (`students_id`)
    REFERENCES `exercise5_1`.`students` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`students`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exercise5_1`.`students` ;

CREATE TABLE IF NOT EXISTS `exercise5_1`.`students` (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

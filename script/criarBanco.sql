-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pv
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `pv` ;

-- -----------------------------------------------------
-- Schema pv
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pv` DEFAULT CHARACTER SET utf8 ;
USE `pv` ;

-- -----------------------------------------------------
-- Table `pv`.`evento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pv`.`evento` ;

CREATE TABLE IF NOT EXISTS `pv`.`evento` (
  `cod` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `data` DATE NOT NULL,
  `valor_entrada` FLOAT NOT NULL,
  PRIMARY KEY (`cod`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

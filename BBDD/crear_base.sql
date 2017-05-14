-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema APP_PROY_SOFT
-- -----------------------------------------------------
-- Base de datos de la aplicación cambialibros
-- 
DROP SCHEMA IF EXISTS `APP_PROY_SOFT` ;

-- -----------------------------------------------------
-- Schema APP_PROY_SOFT
--
-- Base de datos de la aplicación cambialibros
-- 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `APP_PROY_SOFT` DEFAULT CHARACTER SET utf8 ;
USE `APP_PROY_SOFT` ;

-- -----------------------------------------------------
-- Table `APP_PROY_SOFT`.`Usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `APP_PROY_SOFT`.`Usuarios` ;

CREATE TABLE IF NOT EXISTS `APP_PROY_SOFT`.`Usuarios` (
  `nickname` VARCHAR(9) NOT NULL,
  `nombre` VARCHAR(15) NOT NULL,
  `Apellido` VARCHAR(15) NOT NULL,
  `valoracion` DECIMAL(2) NULL,
  `contrasenya` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nickname`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `APP_PROY_SOFT`.`Transacciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `APP_PROY_SOFT`.`Transacciones` ;

CREATE TABLE IF NOT EXISTS `APP_PROY_SOFT`.`Transacciones` (
  `idtransacciones` INT NOT NULL AUTO_INCREMENT,
  `nick_comprador` VARCHAR(9) NOT NULL,
  `nick_vendedor` VARCHAR(9) NOT NULL,
  `titulo_libro` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtransacciones`),
  INDEX `fk_Transacciones_Usuarios1_idx` (`nick_comprador` ASC),
  INDEX `fk_Transacciones_Usuarios2_idx` (`nick_vendedor` ASC),
  CONSTRAINT `fk_Transacciones_Usuarios1`
    FOREIGN KEY (`nick_comprador`)
    REFERENCES `APP_PROY_SOFT`.`Usuarios` (`nickname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transacciones_Usuarios2`
    FOREIGN KEY (`nick_vendedor`)
    REFERENCES `APP_PROY_SOFT`.`Usuarios` (`nickname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `APP_PROY_SOFT`.`Libros`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `APP_PROY_SOFT`.`Libros` ;

CREATE TABLE IF NOT EXISTS `APP_PROY_SOFT`.`Libros` (
  `idLibros` INT NOT NULL AUTO_INCREMENT,
  `nick_duenyo` VARCHAR(9) NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `autor` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(300) NOT NULL,
  `localizacion` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idLibros`),
  INDEX `fk_Libros_Usuarios_idx` (`nick_duenyo` ASC),
  CONSTRAINT `fk_Libros_Usuarios`
    FOREIGN KEY (`nick_duenyo`)
    REFERENCES `APP_PROY_SOFT`.`Usuarios` (`nickname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `APP_PROY_SOFT`.`Lista_libros`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `APP_PROY_SOFT`.`Lista_libros` ;

CREATE TABLE IF NOT EXISTS `APP_PROY_SOFT`.`Lista_libros` (
  `id_list_libros` INT NOT NULL AUTO_INCREMENT,
  `id_libro` INT NOT NULL,
  `nickname` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`id_list_libros`),
  INDEX `fk_Lista_libros_Usuarios1_idx` (`nickname` ASC),
  CONSTRAINT `fk_Lista_libros_Usuarios1`
    FOREIGN KEY (`nickname`)
    REFERENCES `APP_PROY_SOFT`.`Usuarios` (`nickname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `APP_PROY_SOFT`.`Lista_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `APP_PROY_SOFT`.`Lista_users` ;

CREATE TABLE IF NOT EXISTS `APP_PROY_SOFT`.`Lista_users` (
  `id_list_users` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(9) NOT NULL,
  `user_fav` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`id_list_users`),
  INDEX `fk_Lista_users_Usuarios1_idx` (`nickname` ASC),
  CONSTRAINT `fk_Lista_users_Usuarios1`
    FOREIGN KEY (`nickname`)
    REFERENCES `APP_PROY_SOFT`.`Usuarios` (`nickname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `APP_PROY_SOFT`;

DELIMITER $$

USE `APP_PROY_SOFT`$$
DROP TRIGGER IF EXISTS `APP_PROY_SOFT`.`Usuarios_BEFORE_DELETE` $$
USE `APP_PROY_SOFT`$$
CREATE DEFINER = CURRENT_USER TRIGGER `APP_PROY_SOFT`.`Usuarios_BEFORE_DELETE` BEFORE DELETE ON `Usuarios` FOR EACH ROW
BEGIN
	SET SQL_SAFE_UPDATES=0;
	DELETE FROM Transacciones  
	WHERE nick_comprador = old.nickname OR nick_vendedor = old.nickname ;
	SET SQL_SAFE_UPDATES=1;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

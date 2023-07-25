CREATE SCHEMA IF NOT EXISTS `jestor` DEFAULT CHARACTER SET utf8 ;
USE `jestor`;

-- -----------------------------------------------------
-- Table `jestor`.`_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `register_date` DATETIME NOT NULL,
  `role` ENUM('ADMIN', 'USER') NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `jestor`.`token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`token` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `expired` BIT(1) NOT NULL,
  `revoked` BIT(1) NOT NULL,
  `token` VARCHAR(255) NULL DEFAULT NULL,
  `token_type` ENUM('BEARER') NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_pddrhgwxnms2aceeku9s2ewy5` (`token` ASC) VISIBLE,
  INDEX `FKiblu4cjwvyntq3ugo31klp1c6` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKiblu4cjwvyntq3ugo31klp1c6`
    FOREIGN KEY (`user_id`)
    REFERENCES `jestor`.`_user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `jestor`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` ENUM('E', 'S') NOT NULL,
  `color` VARCHAR(10) NOT NULL,
  `description` VARCHAR(50) NOT NULL,
  `icon` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `jestor`.`financial_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`financial_record` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `value` DECIMAL(38,2) NOT NULL,
  `description` VARCHAR(50) NOT NULL,
  `date` DATE NOT NULL,
  `category_id` BIGINT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKcagxqaet3yr6at2d21hgoijp8` (`category_id` ASC) VISIBLE,
  INDEX `FKpi3i4ynamoe8sv8wkbsgqt89t` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKcagxqaet3yr6at2d21hgoijp8`
    FOREIGN KEY (`category_id`)
    REFERENCES `jestor`.`category` (`id`),
  CONSTRAINT `FKpi3i4ynamoe8sv8wkbsgqt89t`
    FOREIGN KEY (`user_id`)
    REFERENCES `jestor`.`_user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
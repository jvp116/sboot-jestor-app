CREATE SCHEMA IF NOT EXISTS `jestor` DEFAULT CHARACTER SET utf8 ;
USE `jestor` ;

-- -----------------------------------------------------
-- Table `jestor`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`usuario` (
  `data_cadastro` DATETIME NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `jestor`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`categoria` (
  `tipo` ENUM('E', 'S') NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cor` VARCHAR(10) NOT NULL,
  `descricao` VARCHAR(50) NOT NULL,
  `icone` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `jestor`.`lancamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`lancamento` (
  `valor` DECIMAL(38,2) NOT NULL,
  `categoria_id` BIGINT NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `usuario_id` BIGINT NOT NULL,
  `descricao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKlea09jmtx2hqws9pua8y3v2kt` (`categoria_id` ASC) VISIBLE,
  INDEX `FKpq8mu65bmp2hw9wl4kdahxajq` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `FKlea09jmtx2hqws9pua8y3v2kt`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `jestor`.`categoria` (`id`),
  CONSTRAINT `FKpq8mu65bmp2hw9wl4kdahxajq`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `jestor`.`usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
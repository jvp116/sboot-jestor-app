CREATE SCHEMA IF NOT EXISTS `jestor` DEFAULT CHARACTER SET utf8 ;
USE `jestor` ;

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
-- Table `jestor`.`grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`grupo` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `jestor`.`permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`permissao` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `jestor`.`grupo_permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`grupo_permissao` (
  `grupo_id` BIGINT NOT NULL,
  `permissao_id` BIGINT NOT NULL,
  PRIMARY KEY (`grupo_id`, `permissao_id`),
  INDEX `FKh21kiw0y0hxg6birmdf2ef6vy` (`permissao_id` ASC) VISIBLE,
  CONSTRAINT `FKh21kiw0y0hxg6birmdf2ef6vy`
    FOREIGN KEY (`permissao_id`)
    REFERENCES `jestor`.`permissao` (`id`),
  CONSTRAINT `FKta4si8vh3f4jo3bsslvkscc2m`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `jestor`.`grupo` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `jestor`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`usuario` (
  `data_cadastro` DATETIME NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
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

-- -----------------------------------------------------
-- Table `jestor`.`usuario_grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jestor`.`usuario_grupo` (
  `grupo_id` BIGINT NOT NULL,
  `usuario_id` BIGINT NOT NULL,
  PRIMARY KEY (`grupo_id`, `usuario_id`),
  INDEX `FKdofo9es0esuiahyw2q467crxw` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `FKdofo9es0esuiahyw2q467crxw`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `jestor`.`usuario` (`id`),
  CONSTRAINT `FKk30suuy31cq5u36m9am4om9ju`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `jestor`.`grupo` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
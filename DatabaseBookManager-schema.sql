SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `DatabaseBookManager` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `DatabaseBookManager` ;

-- -----------------------------------------------------
-- Table `DatabaseBookManager`.`taikhoandangnhap`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DatabaseBookManager`.`taikhoandangnhap` (
  `TenDangNhap` VARCHAR(30) NOT NULL ,
  `MatKhau` VARCHAR(45) NOT NULL ,
  `HoTen` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`TenDangNhap`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseBookManager`.`tacgia`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DatabaseBookManager`.`tacgia` (
  `ID_TacGia` VARCHAR(10) NOT NULL ,
  `TenTacGia` VARCHAR(45) NOT NULL ,
  `NamSinh` DATE NOT NULL ,
  PRIMARY KEY (`ID_TacGia`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseBookManager`.`docgia`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DatabaseBookManager`.`docgia` (
  `ID_DocGia` VARCHAR(10) NOT NULL ,
  `TenDocGia` VARCHAR(45) NOT NULL ,
  `NgheNghiep` VARCHAR(45) NOT NULL ,
  `NgayCapThe` DATE NOT NULL ,
  PRIMARY KEY (`ID_DocGia`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseBookManager`.`sach`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DatabaseBookManager`.`sach` (
  `ID_Sach` VARCHAR(10) NOT NULL ,
  `TenSach` VARCHAR(45) NOT NULL ,
  `LoaiSach` VARCHAR(45) NOT NULL ,
  `Gia` VARCHAR(10) NOT NULL ,
  `tacgia_ID_TacGia` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`ID_Sach`) ,
  INDEX `fk_sach_tacgia` (`tacgia_ID_TacGia` ASC) ,
  CONSTRAINT `fk_sach_tacgia`
    FOREIGN KEY (`tacgia_ID_TacGia` )
    REFERENCES `DatabaseBookManager`.`tacgia` (`ID_TacGia` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseBookManager`.`muontra`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DatabaseBookManager`.`muontra` (
  `ID_MuonTra` VARCHAR(10) NOT NULL ,
  `NgayMuon` DATE NOT NULL ,
  `NgayTra` DATE NOT NULL ,
  `sach_ID_Sach` VARCHAR(10) NOT NULL ,
  `docgia_ID_DocGia` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`ID_MuonTra`) ,
  INDEX `fk_muontra_sach1` (`sach_ID_Sach` ASC) ,
  INDEX `fk_muontra_docgia1` (`docgia_ID_DocGia` ASC) ,
  CONSTRAINT `fk_muontra_sach1`
    FOREIGN KEY (`sach_ID_Sach` )
    REFERENCES `DatabaseBookManager`.`sach` (`ID_Sach` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_muontra_docgia1`
    FOREIGN KEY (`docgia_ID_DocGia` )
    REFERENCES `DatabaseBookManager`.`docgia` (`ID_DocGia` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

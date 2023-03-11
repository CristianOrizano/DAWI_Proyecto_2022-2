CREATE DATABASE  IF NOT EXISTS `cyberelec` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cyberelec`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: cyberelec
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_categoria`
--

DROP TABLE IF EXISTS `tb_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_categoria` (
  `ide_cat` int NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `ide_empre` int DEFAULT NULL,
  PRIMARY KEY (`ide_cat`),
  KEY `fk_empr` (`ide_empre`),
  CONSTRAINT `fk_empr` FOREIGN KEY (`ide_empre`) REFERENCES `tb_empresa` (`ide_empre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categoria`
--

LOCK TABLES `tb_categoria` WRITE;
/*!40000 ALTER TABLE `tb_categoria` DISABLE KEYS */;
INSERT INTO `tb_categoria` VALUES (1,'Cocina',1),(2,'Limpieza',1),(3,'Refrigeracion',1),(4,'Aparato Alumbrado',2),(5,'Electricas',2),(6,'Multimedia',2),(7,'Equipos sonido',3),(8,'Oficina',4),(9,'Iphone',4);
/*!40000 ALTER TABLE `tb_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cliente` (
  `cod_cli` int NOT NULL AUTO_INCREMENT,
  `nom_cli` varchar(20) DEFAULT NULL,
  `ape_cli` varchar(50) DEFAULT NULL,
  `dni` char(9) DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `fecha_naci` date DEFAULT NULL,
  PRIMARY KEY (`cod_cli`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES (1001,'Nini','Salazar','7653288','Femenino','2001-10-10'),(1002,'Cristian','Orizano','86759773','Masculino','2002-10-10'),(1003,'Emma','Salas Ro','63454776','Femenino','2003-10-10'),(1004,'Taylor','Swift Ro','63454776','Femenino','2004-10-10'),(1005,'Oliver','Atonio','63454776','Femenino','2002-10-10'),(1006,'Olivia','Rodrigo','63454776','Femenino','2001-10-10'),(1007,'Liv','Rooney','63454776','Femenino','2002-10-10');
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_detalle`
--

DROP TABLE IF EXISTS `tb_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_detalle` (
  `num_fact` int NOT NULL,
  `codigo` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  `preciovta` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`num_fact`,`codigo`),
  KEY `fkcodi` (`codigo`),
  CONSTRAINT `fkcodi` FOREIGN KEY (`codigo`) REFERENCES `tb_electrodomesticos` (`codigo`),
  CONSTRAINT `fkfactu` FOREIGN KEY (`num_fact`) REFERENCES `tb_factura` (`num_fact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_detalle`
--

LOCK TABLES `tb_detalle` WRITE;
/*!40000 ALTER TABLE `tb_detalle` DISABLE KEYS */;
INSERT INTO `tb_detalle` VALUES (2001,3001,2,1500.00),(2001,3002,4,1000.00),(2001,3003,1,900.00),(2002,3006,2,1500.00),(2002,3007,1,1000.00),(2002,3008,3,900.00),(2002,3009,3,1800.00);
/*!40000 ALTER TABLE `tb_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_electrodomesticos`
--

DROP TABLE IF EXISTS `tb_electrodomesticos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_electrodomesticos` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `ide_cat` int DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `Marca` varchar(50) DEFAULT NULL,
  `estado` int DEFAULT NULL,
  `nom_archivo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_cat` (`ide_cat`),
  CONSTRAINT `fk_cat` FOREIGN KEY (`ide_cat`) REFERENCES `tb_categoria` (`ide_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=3021 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_electrodomesticos`
--

LOCK TABLES `tb_electrodomesticos` WRITE;
/*!40000 ALTER TABLE `tb_electrodomesticos` DISABLE KEYS */;
INSERT INTO `tb_electrodomesticos` VALUES (3001,2,'Lavadora 2022',50,1500.00,'Samsung',1,'2001.jpg'),(3002,3,'Refrigeradora black',50,1000.00,'LG',1,'2005.jpg'),(3003,3,'Aspirador',50,900.00,'Oster',1,'2007.jpg'),(3004,1,'Microondas CMG\'',50,1800.00,'Pool',1,'2008.jpg'),(3005,6,'Smart TV 55',50,1500.00,'Samnsung',1,'3001.png'),(3006,2,'Aspiradora smart',50,1500.00,'Xiaomi',1,'10001.PNG'),(3007,2,'Purificador de aire',50,1000.00,'Home',1,'10002.PNG'),(3008,1,'Air fryer',50,900.00,'Oster',1,'10003.PNG'),(3009,4,'Television 55\'',50,1800.00,'Samnsung',1,'10004.PNG'),(3010,1,'Batidora premium',50,1500.00,'Oster',1,'10005.PNG'),(3011,3,'Congeladora 50 litros',50,1000.00,'Indurama',1,'10006.PNG'),(3012,4,'Control remoto',50,50.00,'Samsung',1,'10007.PNG'),(3013,4,'Blue ray 2020',50,800.00,'LG',1,'10008.PNG'),(3014,4,'Radio Stereo',50,1400.00,'Tunic',1,'10009.PNG'),(3015,1,'Microondas',50,900.00,'Whirpool',1,'10010.PNG'),(3016,1,'Licuadora',50,700.00,'Oster',1,'10011.PNG'),(3017,1,'Olla arrocera',50,400.00,'Oster',1,'10012.PNG'),(3018,1,'Hornilla doble',50,600.00,'Flama',1,'10013.PNG'),(3019,3,'Refrigeradora 2021',50,900.00,'Samsung',1,'10014.PNG');
/*!40000 ALTER TABLE `tb_electrodomesticos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_empresa`
--

DROP TABLE IF EXISTS `tb_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_empresa` (
  `ide_empre` int NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ide_empre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_empresa`
--

LOCK TABLES `tb_empresa` WRITE;
/*!40000 ALTER TABLE `tb_empresa` DISABLE KEYS */;
INSERT INTO `tb_empresa` VALUES (1,'ZetaTex'),(2,'Techniser'),(3,'La Curacao'),(4,'Megatex');
/*!40000 ALTER TABLE `tb_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_enlace`
--

DROP TABLE IF EXISTS `tb_enlace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_enlace` (
  `idenlace` int NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `ruta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idenlace`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_enlace`
--

LOCK TABLES `tb_enlace` WRITE;
/*!40000 ALTER TABLE `tb_enlace` DISABLE KEYS */;
INSERT INTO `tb_enlace` VALUES (1,'Mantener ','/electro/lis'),(2,'Catalogo','/electro/catalogo'),(3,'Carro','/electro/VistaCarro'),(4,'Reporte','/detalle/listaventa'),(5,'Proveedores','/proveedor/lis');
/*!40000 ALTER TABLE `tb_enlace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_factura`
--

DROP TABLE IF EXISTS `tb_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_factura` (
  `num_fact` int NOT NULL AUTO_INCREMENT,
  `fecha_emi` date DEFAULT NULL,
  `cod_tra` int DEFAULT NULL,
  `cod_cli` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`num_fact`),
  KEY `fk_cog` (`cod_tra`),
  KEY `fk_cli` (`cod_cli`),
  CONSTRAINT `fk_cli` FOREIGN KEY (`cod_cli`) REFERENCES `tb_cliente` (`cod_cli`),
  CONSTRAINT `fk_cog` FOREIGN KEY (`cod_tra`) REFERENCES `tb_usuarios` (`cod_usu`)
) ENGINE=InnoDB AUTO_INCREMENT=2003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_factura`
--

LOCK TABLES `tb_factura` WRITE;
/*!40000 ALTER TABLE `tb_factura` DISABLE KEYS */;
INSERT INTO `tb_factura` VALUES (2001,'2022-11-26',2007,1001,7900),(2002,'2022-11-26',2002,1004,12100);
/*!40000 ALTER TABLE `tb_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_proveedor`
--

DROP TABLE IF EXISTS `tb_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_proveedor` (
  `cod_prove` int NOT NULL AUTO_INCREMENT,
  `nom_prove` varchar(20) DEFAULT NULL,
  `ape_prove` varchar(50) DEFAULT NULL,
  `dni` char(9) DEFAULT NULL,
  `dirrecion` varchar(80) DEFAULT NULL,
  `numero_tel` int DEFAULT NULL,
  PRIMARY KEY (`cod_prove`)
) ENGINE=InnoDB AUTO_INCREMENT=2009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_proveedor`
--

LOCK TABLES `tb_proveedor` WRITE;
/*!40000 ALTER TABLE `tb_proveedor` DISABLE KEYS */;
INSERT INTO `tb_proveedor` VALUES (2001,'Emma','Salas Ro','63454776','Calle 236',987456321),(2002,'Taylor','Swift Ro','63454776','Av. proceres',98765423),(2003,'Oliver','Atonio','63454776','Av miraflores',98546123),(2004,'Olivia','Rodrigo','63454776','Calle wilson',963214587),(2005,'Liv','Rooney','63454776','Los postes',987456321),(2006,'Rachel','Blue','63454776','Calle wisconsi',951236487);
/*!40000 ALTER TABLE `tb_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_rol`
--

DROP TABLE IF EXISTS `tb_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_rol` (
  `idrol` int NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_rol`
--

LOCK TABLES `tb_rol` WRITE;
/*!40000 ALTER TABLE `tb_rol` DISABLE KEYS */;
INSERT INTO `tb_rol` VALUES (1,'Gerente'),(2,'Admistrador'),(3,'Vendedor');
/*!40000 ALTER TABLE `tb_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_rol_enlace`
--

DROP TABLE IF EXISTS `tb_rol_enlace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_rol_enlace` (
  `idrol` int NOT NULL,
  `idenlace` int NOT NULL,
  PRIMARY KEY (`idrol`,`idenlace`),
  KEY `fkenlace` (`idenlace`),
  CONSTRAINT `fkenlace` FOREIGN KEY (`idenlace`) REFERENCES `tb_enlace` (`idenlace`),
  CONSTRAINT `fkidrole` FOREIGN KEY (`idrol`) REFERENCES `tb_rol` (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_rol_enlace`
--

LOCK TABLES `tb_rol_enlace` WRITE;
/*!40000 ALTER TABLE `tb_rol_enlace` DISABLE KEYS */;
INSERT INTO `tb_rol_enlace` VALUES (1,1),(2,1),(1,2),(2,2),(3,2),(1,3),(2,3),(3,3),(1,4),(1,5);
/*!40000 ALTER TABLE `tb_rol_enlace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuarios`
--

DROP TABLE IF EXISTS `tb_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuarios` (
  `cod_usu` int NOT NULL AUTO_INCREMENT,
  `nom_empl` varchar(20) DEFAULT NULL,
  `ape_empl` varchar(50) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `login` varchar(50) DEFAULT NULL,
  `clave` varchar(500) DEFAULT NULL,
  `idrol` int DEFAULT NULL,
  PRIMARY KEY (`cod_usu`),
  KEY `fk_rolfor` (`idrol`),
  CONSTRAINT `fk_rolfor` FOREIGN KEY (`idrol`) REFERENCES `tb_rol` (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=2008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuarios`
--

LOCK TABLES `tb_usuarios` WRITE;
/*!40000 ALTER TABLE `tb_usuarios` DISABLE KEYS */;
INSERT INTO `tb_usuarios` VALUES (2001,'Cristian','Orizano Huyhua','2001-09-10','cristian10@gmail.com','$2a$10$jkq/4kOT3mt6sYvFHFoebO/3wx1AZRS4Nba7Z.KAG1vD9zau40Gxy',1),(2002,'Hans','Nolasco Ramires','2001-09-10','hans10@gmail.com','$2a$10$jkq/4kOT3mt6sYvFHFoebO/3wx1AZRS4Nba7Z.KAG1vD9zau40Gxy',2),(2003,'Sofia','salzar','2001-09-10','sofia10@gmail.com','$2a$10$jkq/4kOT3mt6sYvFHFoebO/3wx1AZRS4Nba7Z.KAG1vD9zau40Gxy',1),(2004,'Surgical','Goblin','2001-09-10','surgi10@gmail.com','$2a$10$jkq/4kOT3mt6sYvFHFoebO/3wx1AZRS4Nba7Z.KAG1vD9zau40Gxy',1),(2005,'Olivia','Rog','2001-09-10','livia10@gmail.com','$2a$10$jkq/4kOT3mt6sYvFHFoebO/3wx1AZRS4Nba7Z.KAG1vD9zau40Gxy',1),(2006,'Mugi','Light','2001-09-10','mugi10@gmail.com','$2a$10$jkq/4kOT3mt6sYvFHFoebO/3wx1AZRS4Nba7Z.KAG1vD9zau40Gxy',1),(2007,'Carlos','Vega Ramires','2001-09-10','carlos@gmail.com','$2a$10$jkq/4kOT3mt6sYvFHFoebO/3wx1AZRS4Nba7Z.KAG1vD9zau40Gxy',3);
/*!40000 ALTER TABLE `tb_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cyberelec'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-26 22:55:04

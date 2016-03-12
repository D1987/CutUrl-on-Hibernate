-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: diplom
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `reference`
--

DROP TABLE IF EXISTS `reference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference` (
  `id_reference` int(11) NOT NULL AUTO_INCREMENT,
  `full_ref` longtext NOT NULL,
  `cut_ref` varchar(22) NOT NULL,
  `description` mediumtext,
  `count` varchar(100) DEFAULT '0',
  `tag` varchar(80) DEFAULT NULL,
  `qrcode` longblob,
  `id_users` int(11) DEFAULT NULL,
  `idU` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_reference`),
  UNIQUE KEY `cut_ref_UNIQUE` (`cut_ref`),
  KEY `id_users_idx` (`id_users`),
  CONSTRAINT `id_users` FOREIGN KEY (`id_users`) REFERENCES `users` (`id_users`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=336 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference`
--

LOCK TABLES `reference` WRITE;
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
INSERT INTO `reference` VALUES (256,'http://devcolibri.com/1250','localhost:81/by/Pf3JCG','devcolibri','28','dev','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0d\0\0\0d\0\0\0��\0\0�IDATx\�\�\�Q�\� \0\�\��\�\�\�^@)f�v\�\�\�\� \���\�x��,X`��\�c=�X�孫w\��iX{w�\�\�bU\�a����r���\�>\�\Zٚ!,X�Ǫ�,X�\�\�!,X󱪾�u�-\\6,X�b%�E\r#\�)���u�;:-W\�P��\�`�\��7�{\Z:I�`�Ŋ%\�\�grb\'�\���\�%\�\r|�`]�Kb���\�\�7�,X��\��Jyr\"��kV\�zU5\�#�\���\�!9R�\r%i`��+�\rT\�YU�נ<�\�=�굃\�jc�,X7bUE�c��t\�+G�`%˼\�+ΰ`�+Y�ZRg\r\Z��X\�N\�U%*X�`�gb��zu	��aU�����pJ\�(�s\�\�do)�cuVg\�&9�d\�\�4,,X�`	X�`��K�\�\�J7�5\0\0\0\0IEND�B`�',78,80),(272,'https://stepic.org/users/2054363/learn','localhost:81/by/osWU3T','','2','stepic','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0d\0\0\0d\0\0\0��\0\0�IDATx\�\�\�;\�0�\��ҡ����\�_X�0	�2Qd\�\�m<X�`��,X�`�1\�UO\�\�4��3�k7��\'��\��n�{y|��`\��\��oZR1��f\�뱊I�\���,X�\�J]մ�?\�\����M�\�=\�?X�uGj\��b�:�V��\��\�\�$���Vʱ�\�O5~���`%�&\�;�4�WX�\�\�\\<�/{`=�{R\�j)?\ZL\�a�\�k\\�X\�JU\�`�\��k��E\�\��s�`�\�HO�:����,X�Ǭb��*�����`�5.�wThD	��X�\�X\�\�uV<B+\��,XaO\Z\�q��\�cw\�\�\�7��2,X�\�1+:�5\��X�bt�{_�?�\�\���qݝx\�	��X,X�`��e��,X\�w|\0\nd�/J��\0\0\0\0IEND�B`�',80,NULL),(288,'http://expo.hh.ru/it/?utm_source=smm&utm_medium=vkontakte&utm_content=hh_post&utm_campaign=soisk_post_cot_proger_2801','localhost:81/by/1WPfT8','','1','','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0d\0\0\0d\0\0\0��\0\0}IDATx\�\�\�\��0@�o:i�\�֟d\0{�Qd\�\�ϸ=.�`��X�`��0\�\�w�\�]ư�Vm\�\�Yd|��`\�U[@�/>CX�^��\�G��`�\Z�u\��/c�\��omw`��V�&\\yO��Aݑ\'Cj\�}j+�Fa7\�墳bh\'	��X��\�w\�͉զ\n\�&X��\�7\�K�+,X�`��\�E��,,Xa5S�q	G�9�`m�լ15;��\�L�SX�\�d��\����F\�\�a�\Z\\ϊg�/�Q\�\0\����:8��։X�\�<\\���\�Z��ɿ\�]�rX�ށՌP�B�\�\Z4,X\�b5\�H�.\\\�\�a�� Vj\�5�٧�a��m���*\��`\�\�\���\�f#���R�����e<,X[vwN�`���,X�`-$\�M|x\0\0\0\0IEND�B`�',80,NULL),(290,'http://forum.grodno.net/index.php?board=55.0','localhost:81/by/x1N4mo','','1','','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0d\0\0\0d\0\0\0��\0\0�IDATx\�\�\�A�� P\�\�ŬgJ�\"�Xv\n\�C\��\�n�,X�`���\0,X��\�\�;W�\rc�a�\�\rk\�\r��Wͱy�~/X��º��ϛ\�н�F\��XC5,X�f`\r]�\�R����6,X\�\�jf��rN��IՑҷ?����V�_��<[I�k7�f� \\#�,X/\�\Z\Z\\*��r��`m�5��\�|�>\�$�`����\�\�ͭ�\�P�=�j�\�f\�\�L~�\�\���5\'th\�D�\�G\�\�,X/\�z6^OՄ`���f�*@5�\�1AX��\�J��`޽`�\�\r+~�/\�=���?Vj��=�T�\�X�S\��,X�>�S\�ʹZ�0,X`5�N\�E���`�\�\r+N�\n\�k+&,X/\�J%�\�m��\�\ra�\�K�,X�4X�`��K�m?\�O�o\�\�\0\0\0\0IEND�B`�',80,NULL);
/*!40000 ALTER TABLE `reference` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-04 12:12:23

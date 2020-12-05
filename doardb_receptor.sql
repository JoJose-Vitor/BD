CREATE TABLE `receptor` (
  `receptorID` int NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `nome` varchar(80) DEFAULT NULL,
  `cnpj` varchar(16) DEFAULT NULL,
  `telefone` varchar(16) DEFAULT NULL,
  `endereco` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`receptorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

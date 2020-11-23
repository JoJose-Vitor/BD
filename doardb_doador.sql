CREATE TABLE `doador` (
  `doadorID` int NOT NULL,
  `login` varchar(20) DEFAULT NULL,
  `senha` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `nome` varchar(80) DEFAULT NULL,
  `rg` varchar(10) DEFAULT NULL,
  `cpf` varchar(12) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `endereco` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`doadorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
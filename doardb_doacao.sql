CREATE TABLE `doacao` (
  `doacaoID` int NOT NULL,
  `valor` float DEFAULT NULL,
  `entrada` varchar(11) DEFAULT NULL,
  `saida` varchar(11) DEFAULT NULL,
  `doadorID` int DEFAULT NULL,
  `receptorID` int DEFAULT NULL,
  `comprovante` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`doacaoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
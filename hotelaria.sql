-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 09/12/2024 às 21:03
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `hotelaria`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `hospedes`
--

CREATE TABLE `hospedes` (
  `id_hospede` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `idade` int(11) DEFAULT NULL,
  `quemCadastrou` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `hospedes`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `quartos`
--

CREATE TABLE `quartos` (
  `id_quarto` int(11) NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `disponivel` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `quartos`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `quartosreservados`
--

CREATE TABLE `quartosreservados` (
  `id_quartoReservado` int(11) NOT NULL,
  `hospede` int(11) DEFAULT NULL,
  `quarto` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `data_entrada` varchar(255) DEFAULT NULL,
  `data_saida` varchar(255) DEFAULT NULL,
  `id_reserva` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `quartosreservados`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `reservas`
--

CREATE TABLE `reservas` (
  `id_reserva` int(11) NOT NULL,
  `hospede_id` int(11) DEFAULT NULL,
  `quarto_id` int(11) DEFAULT NULL,
  `servico` varchar(255) DEFAULT NULL,
  `data_entrada` varchar(255) DEFAULT NULL,
  `data_saida` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `metodo_pagamento` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `reservas`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `servicos`
--

CREATE TABLE `servicos` (
  `id_servico` int(11) NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `preco` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `servicos`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `idade` int(11) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `is_adm` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `hospedes`
--
ALTER TABLE `hospedes`
  ADD PRIMARY KEY (`id_hospede`);

--
-- Índices de tabela `quartos`
--
ALTER TABLE `quartos`
  ADD PRIMARY KEY (`id_quarto`);

--
-- Índices de tabela `quartosreservados`
--
ALTER TABLE `quartosreservados`
  ADD PRIMARY KEY (`id_quartoReservado`),
  ADD KEY `fk_hospede` (`hospede`),
  ADD KEY `fk_quarto` (`quarto`),
  ADD KEY `fk_reserva` (`id_reserva`);

--
-- Índices de tabela `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `fk_hospede_reserva` (`hospede_id`),
  ADD KEY `fk_quarto_reserva` (`quarto_id`);

--
-- Índices de tabela `servicos`
--
ALTER TABLE `servicos`
  ADD PRIMARY KEY (`id_servico`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `hospedes`
--
ALTER TABLE `hospedes`
  MODIFY `id_hospede` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT de tabela `quartos`
--
ALTER TABLE `quartos`
  MODIFY `id_quarto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de tabela `quartosreservados`
--
ALTER TABLE `quartosreservados`
  MODIFY `id_quartoReservado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de tabela `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT de tabela `servicos`
--
ALTER TABLE `servicos`
  MODIFY `id_servico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `quartosreservados`
--
ALTER TABLE `quartosreservados`
  ADD CONSTRAINT `fk_hospede` FOREIGN KEY (`hospede`) REFERENCES `hospedes` (`id_hospede`),
  ADD CONSTRAINT `fk_quarto` FOREIGN KEY (`quarto`) REFERENCES `quartos` (`id_quarto`),
  ADD CONSTRAINT `fk_reserva` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reserva`);

--
-- Restrições para tabelas `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `fk_hospede_reserva` FOREIGN KEY (`hospede_id`) REFERENCES `hospedes` (`id_hospede`),
  ADD CONSTRAINT `fk_quarto_reserva` FOREIGN KEY (`quarto_id`) REFERENCES `quartos` (`id_quarto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

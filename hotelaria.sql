-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 27/11/2024 às 18:59
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

INSERT INTO `hospedes` (`id_hospede`, `nome`, `email`, `cpf`, `idade`, `quemCadastrou`) VALUES
(6, 'Felipe Hospede', 'felipe@gmail.com', '111.111.111-11', 14, '027.474.690-50');

-- --------------------------------------------------------

--
-- Estrutura para tabela `pagamentos`
--

CREATE TABLE `pagamentos` (
  `id_pagamento` int(11) NOT NULL,
  `tipo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

INSERT INTO `quartos` (`id_quarto`, `tipo`, `numero`, `preco`, `disponivel`) VALUES
(7, 'Solo', '170', 300, 'Indisponível'),
(9, 'Quarto Teste', '201', 500, 'Indisponível'),
(10, 'Teste 2 Quarto', '809', 100, 'Indisponível'),
(11, 'aaa', '42', 12, 'Indisponível');

-- --------------------------------------------------------

--
-- Estrutura para tabela `quartosreservados`
--

CREATE TABLE `quartosreservados` (
  `id_quartoReservado` int(11) NOT NULL,
  `hospede` varchar(255) DEFAULT NULL,
  `quarto` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `data_entrada` varchar(255) DEFAULT NULL,
  `data_saida` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `quartosreservados`
--

INSERT INTO `quartosreservados` (`id_quartoReservado`, `hospede`, `quarto`, `valor`, `data_entrada`, `data_saida`) VALUES
(1, 'Felipe Hospede', '809', 130, '09/10/2024', '09/11/2024'),
(2, 'Felipe Hospede', '42', 42, '20/02/2024', '20/03/2024'),
(3, 'Felipe Hospede', '42', 42, '20/02/2024', '20/03/2024'),
(4, 'Felipe Hospede', '42', 42, '20/02/2024', '20/03/2024');

-- --------------------------------------------------------

--
-- Estrutura para tabela `reservas`
--

CREATE TABLE `reservas` (
  `id_reserva` int(11) NOT NULL,
  `hospede` varchar(255) DEFAULT NULL,
  `quarto` varchar(255) DEFAULT NULL,
  `servico` varchar(255) DEFAULT NULL,
  `data_entrada` varchar(255) DEFAULT NULL,
  `data_saida` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `reservas`
--

INSERT INTO `reservas` (`id_reserva`, `hospede`, `quarto`, `servico`, `data_entrada`, `data_saida`, `total`) VALUES
(6, '5 - Felipe Monari', 'Casal - R$450 - N°304', 'Roupa Lavada - R$40', '20/05/2024', '20/06/2024', 490),
(7, '6 - Felipe Hospede', 'Solo - R$300 - N°170', 'Lava roupa - R$30', '27/11/2024', '28/11/2024', 330),
(8, '6 - Felipe Hospede', 'Quarto Teste - R$500 - N°201', 'Lava roupa - R$30', '30/12/2024', '02/01/2025', 530),
(9, '6 - Felipe Hospede', 'Teste 2 Quarto - R$100 - N°809', 'Lava roupa - R$30', '09/10/2024', '09/11/2024', 130),
(10, '6 - Felipe Hospede', 'aaa - R$12 - N°42', 'Lava roupa - R$30', '20/02/2024', '20/03/2024', 42),
(11, '6 - Felipe Hospede', 'aaa - R$12 - N°42', 'Lava roupa - R$30', '20/02/2024', '20/03/2024', 42),
(12, '6 - Felipe Hospede', 'aaa - R$12 - N°42', 'Lava roupa - R$30', '20/02/2024', '20/03/2024', 42);

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

INSERT INTO `servicos` (`id_servico`, `tipo`, `preco`) VALUES
(3, 'Lava roupa', 30);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `idade` int(11) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nome`, `idade`, `cpf`, `senha`) VALUES
(15, 'Felipe Eduardo Monari', 19, '027.474.690-50', 'monari');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `hospedes`
--
ALTER TABLE `hospedes`
  ADD PRIMARY KEY (`id_hospede`);

--
-- Índices de tabela `pagamentos`
--
ALTER TABLE `pagamentos`
  ADD PRIMARY KEY (`id_pagamento`);

--
-- Índices de tabela `quartos`
--
ALTER TABLE `quartos`
  ADD PRIMARY KEY (`id_quarto`);

--
-- Índices de tabela `quartosreservados`
--
ALTER TABLE `quartosreservados`
  ADD PRIMARY KEY (`id_quartoReservado`);

--
-- Índices de tabela `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`);

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
  MODIFY `id_hospede` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `pagamentos`
--
ALTER TABLE `pagamentos`
  MODIFY `id_pagamento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `quartos`
--
ALTER TABLE `quartos`
  MODIFY `id_quarto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `quartosreservados`
--
ALTER TABLE `quartosreservados`
  MODIFY `id_quartoReservado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `servicos`
--
ALTER TABLE `servicos`
  MODIFY `id_servico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

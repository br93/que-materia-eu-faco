INSERT INTO tb_cursos (nome, matriz, periodos) VALUES
	('Placeholder', '000', '8');

INSERT INTO tb_disciplinas (id, nome, codigo, carga_horaria) VALUES
	('38', 'A Presença Africana no Brasil: Tecnologia e Trabalho', 'ES70J', '30'),
	('39', 'Acessibilidade e Inclusão Digital', 'CSH42', '45'),
	('40', 'Algoritmos de Programação', 'ELB11', '45'),
	('41', 'Banco de Dados 2', 'CSB41', '60'),
	('42', 'Capitalismo Contemporâneo e Economia Política', 'FCH7HC', '45'),
	('43', 'Circuitos Digitais', 'EEB31', '90'),
	('44', 'Ciências do Ambiente', 'QB70E', '30'),
	('45', 'Ciências do Ambiente', 'QB70J', '30'),
	('46', 'Computação e Sociedade', 'CSH44', '45'),
	('47', 'Computação em Nuvem', 'CSM45', '60'),
	('48', 'Comunicação de Dados', 'CSR31', '30'),
	('49', 'Comunicação Linguística', 'CE70A', '30'),
	('50', 'Comunicação Oral e Escrita', 'CE70B', '30'),
	('51', 'Comunicação Oral e Escrita', 'CE70H', '30'),
	('52', 'Comunicação Oral e Escrita', 'C0E70A', '30'),
	('53', 'Ciências das Redes', 'CSI31', '60'),
	('54', 'Cálculo Diferencial e Integral 1', 'MA71A', '90'),
	('55', 'Cálculo Diferencial e Integral 1', 'MA71H', '90'),
	('56', 'Cálculo Numérico', 'MA70C', '60'),
	('57', 'Desenvolvimento de Aplicações Web', 'CSM41', '60'),
	('58', 'Dimensão Ambiental na Gestão Urbana', 'FCH7XF', '45'),
	('59', 'Economia', 'GE70D', '30'),
	('60', 'Fundamentos da Ética', 'ES70H', '30'),
	('61', 'Geometria Analítica', 'MA71I', '45'),
	('62', 'Geometria Computacional', 'CSA45', '60'),
	('63', 'Gerência de Projetos', 'CSE47', '45'),
	('64', 'Gestão de Oportunidades', 'GE70K', '30'),
	('65', 'Gestão de Pessoas', 'GE70F', '30'),
	('66', 'Gestão de Pessoas', 'GE79A', '30'),
	('67', 'Gestão Financeira', 'GE70H', '30'),
	('68', 'Gestão Mercadológica', 'GE70O', '30'),
	('69', 'História da Técnica e da Tecnologia', 'ES70N', '30'),
	('70', 'História Geral da Economia', 'FCH7HB', '45'),
	('71', 'Infraestrutura de Lans Hierarquicas', 'CSR42', '60'),
	('72', 'Introdução a Ciências de Dados', 'CSB53', '60'),
	('73', 'Libras A', 'ED70T', '30'),
	('74', 'Libras B', 'ED70U', '30'),
	('75', 'Lógica Reconfigurável', 'CSW42', '60'),
	('76', 'Metropolização Contemporânea: Tecnologia e Território', 'FCH7GA', '45'),
	('77', 'Oficina de Redes', 'CSR41', '60'),
	('78', 'Políticas Públicas', 'ES60J', '30'),
	('79', 'Programação para Dispositivos Móveis e Sem Fio', 'CSM43', '60'),
	('80', 'Psicologia Aplicada Ao Trabalho', 'ES72C', '30'),
	('81', 'Segurança de Redes e Sistemas', 'CSR44', '60'),
	('82', 'Sistemas Legados', 'CSE44', '45'),
	('83', 'Sistemas Microcontrolados', 'CSW40', '60'),
	('84', 'Smart Challenges', 'ELSC01', '30'),
	('85', 'Smart Projects', 'ELSP01', '30'),
	('86', 'Sociedade e Política no Brasil', 'ES70Q', '30'),
	('87', 'Tecnologia e Sociedade', 'ES70R', '30'),
	('88', 'Tecnologia, Trabalho e Saúde', 'FCH7SC', '45'),
	('89', 'Teoria dos Grafos', 'CSA42', '60'),
	('90', 'Web Design', 'DI84D', '75'),
	('91', 'Álgebra Linear', 'MA72I', '60');	
	
INSERT INTO tb_itens_matriz (id, disciplina_id, periodo, pre_requisitos, curso_id, tipo_disciplina_id, peso) VALUES
	('38', '38', '5', '0', '1', '4', 0.0),
	('39', '39', '4', '0', '1', '3', 0.0),
	('40', '40', '5', '0', '1', '3', 0.0),
	('41', '41', '4', '0', '1', '3', 0.0),
	('42', '42', '5', '0', '1', '4', 0.0),
	('43', '43', '4', '2', '1', '3', 0.0),
	('44', '44', '5', '0', '1', '4', 0.0),
	('45', '45', '5', '0', '1', '4', 0.0),
	('46', '46', '4', '0', '1', '3', 0.0),
	('47', '47', '4', '0', '1', '3', 0.0),
	('48', '48', '4', '0', '1', '3', 0.0),
	('49', '49', '5', '0', '1', '4', 0.0),
	('50', '50', '5', '0', '1', '4', 0.0),
	('51', '51', '5', '0', '1', '4', 0.0),
	('52', '52', '5', '0', '1', '4', 0.0),
	('53', '53', '4', '0', '1', '3', 0.0),
	('56', '56', '4', '0', '1', '3', 0.0),
	('57', '57', '4', '0', '1', '3', 0.0),
	('58', '58', '5', '0', '1', '4', 0.0),
	('59', '59', '5', '0', '2', '1', 0.0),
	('60', '60', '5', '0', '1', '4', 0.0),
	('62', '62', '4', '0', '1', '3', 0.0),
	('63', '63', '4', '0', '1', '3', 0.0),
	('70', '70', '5', '0', '1', '4', 0.0),
	('71', '71', '4', '0', '1', '3', 0.0),
	('72', '72', '4', '0', '1', '3', 0.0),
	('73', '73', '5', '0', '1', '4', 0.0),
	('74', '74', '5', '0', '1', '4', 0.0),
	('75', '75', '4', '0', '1', '3', 0.0),
	('76', '76', '5', '0', '1', '4', 0.0),
	('77', '77', '4', '0', '1', '3', 0.0),
	('78', '78', '5', '0', '1', '4', 0.0),
	('79', '79', '4', '0', '1', '3', 0.0),
	('81', '81', '4', '0', '1', '3', 0.0),
	('82', '82', '4', '0', '1', '3', 0.0),
	('83', '83', '4', '1', '1', '3', 0.0),
	('84', '84', '5', '0', '1', '4', 0.0),
	('85', '85', '5', '0', '1', '4', 0.0),
	('88', '88', '5', '0', '1', '4', 0.0),
	('89', '89', '4', '0', '1', '3', 0.0),
	('90', '90', '4', '0', '1', '3', 0.0);

INSERT INTO tb_disciplinas_requisitos (disciplina_id, requisito_id) VALUES
	('39', '14'),
	('41', '25'),
	('43', '18'),
	('46', '14'),
	('46', '19'),
	('53', '15'),
	('56', '1'),
	('56', '3'),
	('57', '25'),
	('57', '5'),
	('62', '24'),
	('62', '3'),
	('63', '30'),
	('71', '31'),
	('72', '25'),
	('75', '83'),
	('77', '27'),
	('79', '5'),
	('81', '31'),
	('82', '5'),
	('83', '43'),
	('89', '9');
	
INSERT INTO tb_turmas (id, codigo, disciplina_id) VALUES
	('92', 'ES70J-S01', '38'),
	('93', 'ES70J-S02', '38'),
	('94', 'ES70J-S03', '38'),
	('95', 'ES70J-S04', '38'),
	('96', 'ES70J-S05', '38'),
	('97', 'CSH42-S73', '39'),
	('98', 'ELB11-S11', '40'),
	('99', 'CSB41-S73', '41'),
	('100', 'FCH7HC-S01', '42'),
	('101', 'FCH7HC-S02', '42'),
	('102', 'EEB31-S71', '43'),
	('103', 'QB70E-S21', '44'),
	('104', 'QB70E-S23', '44'),
	('105', 'QB70E-S25', '44'),
	('106', 'QB70E-S53', '44'),
	('107', 'QB70E-S71', '44'),
	('108', 'QB70J-S11', '45'),
	('109', 'CSH44-S73', '46'),
	('110', 'CSM45-S71', '47'),
	('111', 'CSR31-S71', '48'),
	('112', 'CE70A-S21', '49'),
	('113', 'CE70A-S23', '49'),
	('114', 'CE70A-S25', '49'),
	('115', 'CE70B-S15', '50'),
	('116', 'CE70B-S31', '50'),
	('117', 'CE70B-S51', '50'),
	('118', 'CE70B-S53', '50'),
	('119', 'CE70B-S55', '50'),
	('120', 'CE70B-S61', '50'),
	('121', 'CE70B-S81', '50'),
	('122', 'CE70B-S83', '50'),
	('123', 'CE70B-S95', '50'),
	('124', 'CE70H-S41', '51'),
	('125', 'CE70H-S43', '51'),
	('126', 'C0E70A-S93', '52'),
	('127', 'CSI31-S73', '53'),
	('128', 'MA71A-S11', '54'),
	('129', 'MA71A-S15', '54'),
	('130', 'MA71A-S21', '54'),
	('131', 'MA71A-S23', '54'),
	('132', 'MA71A-S25', '54'),
	('133', 'MA71A-S51', '54'),
	('134', 'MA71A-S55', '54'),
	('135', 'MA71A-S61', '54'),
	('136', 'MA71A-S71', '54'),
	('137', 'MA71A-S81', '54'),
	('138', 'MA71A-T81', '54'),
	('139', 'MA71H-S41', '55'),
	('140', 'MA71H-S43', '55'),
	('141', 'MA70C-S21', '56'),
	('142', 'MA70C-S23', '56'),
	('143', 'MA70C-S25', '56'),
	('144', 'MA70C-S41', '56'),
	('145', 'MA70C-S55', '56'),
	('146', 'MA70C-S56', '56'),
	('147', 'CSM41-S73', '57'),
	('148', 'FCH7XF-S01', '58'),
	('149', 'GE70D-S21', '59'),
	('150', 'GE70D-S23', '59'),
	('151', 'GE70D-S25', '59'),
	('152', 'GE70D-S43', '59'),
	('153', 'GE70D-S51', '59'),
	('154', 'GE70D-S71', '59'),
	('155', 'ES70H-S01', '60'),
	('156', 'ES70H-S15', '60'),
	('157', 'ES70H-S31', '60'),
	('158', 'ES70H-S41', '60'),
	('159', 'ES70H-S43', '60'),
	('160', 'ES70H-S51', '60'),
	('161', 'ES70H-S93', '60'),
	('162', 'MA71I-S41', '61'),
	('163', 'MA71I-S43', '61'),
	('164', 'CSA45-S71', '62'),
	('165', 'CSE47-S73', '63'),
	('166', 'GE70K-S93', '64'),
	('167', 'GE70F-S51', '65'),
	('168', 'GE70F-T81', '65'),
	('169', 'GE79A-S41', '66'),
	('170', 'GE79A-S43', '66'),
	('171', 'GE70H-S01', '67'),
	('172', 'GE70H-S02', '67'),
	('173', 'GE70H-S51', '67'),
	('174', 'GE70H-S53', '67'),
	('175', 'GE70H-S71', '67'),
	('176', 'GE70O-S01', '68'),
	('177', 'GE70O-S31', '68'),
	('178', 'ES70N-S01', '69'),
	('179', 'ES70N-S02', '69'),
	('180', 'ES70N-S03', '69'),
	('181', 'ES70N-S04', '69'),
	('182', 'ES70N-S05', '69'),
	('183', 'ES70N-S31', '69'),
	('184', 'ES70N-S32', '69'),
	('185', 'FCH7HB-S01', '70'),
	('186', 'FCH7HB-S02', '70'),
	('187', 'CSR42-S73', '71'),
	('188', 'CSB53-S73', '72'),
	('189', 'ED70T-S01', '73'),
	('190', 'ED70T-S61', '73'),
	('191', 'ED70T-S81', '73'),
	('192', 'ED70T-S83', '73'),
	('193', 'ED70T-S85', '73'),
	('194', 'ED70T-S91', '73'),
	('195', 'ED70U-S61', '74'),
	('196', 'ED70U-S81', '74'),
	('197', 'ED70U-S83', '74'),
	('198', 'ED70U-S85', '74'),
	('199', 'ED70U-S91', '74'),
	('200', 'CSW42-S71', '75'),
	('201', 'FCH7GA-S01', '76'),
	('202', 'FCH7GA-S02', '76'),
	('203', 'FCH7GA-S03', '76'),
	('204', 'CSR41-S73', '77'),
	('205', 'ES60J-S01', '78'),
	('206', 'CSM43-S73', '79'),
	('207', 'ES72C-S41', '80'),
	('208', 'ES72C-S43', '80'),
	('209', 'CSR44-S71', '81'),
	('210', 'CSE44-S73', '82'),
	('211', 'CSW40-S71', '83'),
	('212', 'ELSC01-S01', '84'),
	('213', 'ELSC01-S02', '84'),
	('214', 'ELSP01-S01', '85'),
	('215', 'ES70Q-S01', '86'),
	('216', 'ES70Q-S02', '86'),
	('217', 'ES70R-S01', '87'),
	('218', 'ES70R-S15', '87'),
	('219', 'ES70R-S31', '87'),
	('220', 'ES70R-S32', '87'),
	('221', 'FCH7SC-S01', '88'),
	('222', 'CSA42-S73', '89'),
	('223', 'DI84D-M31', '90'),
	('224', 'DI84D-N31', '90'),
	('225', 'MA72I-S41', '91'),
	('226', 'MA72I-S43', '91');	

INSERT INTO tb_horario_aula (turma_id, dia_id, horario_id) VALUES
	('92', '1', '4'), ('92', '1', '5'),
	('93', '1', '15'), ('93', '1', '16'),
	('94', '4', '15'), ('94', '4', '16'),
	('95', '3', '8'), ('95', '3', '9'),
	('96', '2', '8'), ('96', '2', '9'),
	('97', '1', '13'), ('97', '1', '11'), ('97', '1', '12'),
	('98', '1', '13'), ('98', '1', '14'), ('98', '1', '12'),
	('99', '3', '8'), ('99', '3', '9'), ('99', '5', '8'), ('99', '5', '9'),
	('100', '4', '10'), ('100', '4', '11'), ('100', '4', '12'),
	('101', '2', '13'), ('100', '2', '14'), ('100', '2', '15'),
	('102', '4', '7'), ('102', '4', '8'), ('102', '4', '9'), ('102', '4', '10'), ('102', '5', '2'), ('102', '5', '3'), ('102', '5', '4'), ('102', '5', '5'),
	('103', '5', '13'), ('103', '5', '12'),
	('104', '4', '2'), ('104', '4', '3'),
	('105', '3', '1'), ('105', '3', '2'),
	('106', '2', '8'), ('106', '2', '9'),
	('107', '4', '4'), ('107', '4', '5'),  
	('108', '4', '13'), ('108', '4', '14'),
	('109', '3', '10'), ('109', '3', '11'), ('109', '3', '12'),  
	('110', '4', '7'), ('110', '4', '8'), ('110', '4', '9'), ('110', '4', '10'),
	('111', '3', '8'), ('111', '3', '9'),
	('112', '2', '14'), ('112', '2', '15'),
	('113', '1', '5'), ('113', '1', '6'),
	('114', '4', '3'), ('114', '4', '4'),
	('115', '2', '11'), ('115', '2', '12'),
	('116', '3', '5'), ('116', '3', '6'),
	('117', '3', '13'), ('117', '3', '12'),
	('118', '1', '8'), ('118', '1', '9'),
	('119', '2', '8'), ('119', '2', '9'),
	('120', '1', '10'), ('120', '1', '11'),
	('121', '2', '10'), ('121', '2', '11'),
	('122', '2', '1'), ('122', '2', '2'),
	('123', '1', '13'), ('123', '1', '14'),
	('124', '3', '8'), ('124', '3', '9'),
	('125', '3', '10'), ('125', '3', '11'),
	('126', '2', '5'), ('126', '2', '6'),
	('127', '4', '13'), ('127', '4', '14'), ('127', '4', '15'), ('127', '4', '12'),
	('128', '1', '10'), ('128', '1', '11'), ('128', '4', '10'), ('128', '4', '11'), ('128', '5', '10'), ('128', '5', '11'), 
	('129', '1', '7'), ('129', '1', '8'), ('129', '1', '9'), ('129', '4', '7'), ('129', '4', '8'), ('129', '4', '9'),
	('130', '2', '16'), ('130', '2', '17'), ('130', '3', '13'), ('130', '3', '14'), ('130', '4', '13'), ('130', '4', '12'),  
	('131', '2', '7'), ('131', '2', '8'), ('131', '4', '5'), ('131', '4', '6'), ('131', '5', '5'), ('131', '5', '6'),
	('132', '1', '1'), ('132', '1', '2'), ('132', '4', '1'), ('132', '4', '2'), ('132', '5', '2'), ('132', '5', '3'),
	('133', '1', '13'), ('133', '1', '14'), ('133', '2', '11'), ('133', '2', '12'), ('133', '3', '10'), ('133', '3', '11'),
	('134', '1', '2'), ('134', '1', '3'), ('134', '2', '3'), ('134', '2', '3'), ('134', '3', '2'), ('134', '3', '3'),
	('135', '2', '4'), ('135', '2', '5'), ('135', '3', '4'), ('135', '3', '5'), ('135', '4', '4'), ('135', '4', '5'),
	('136', '2', '4'), ('136', '2', '5'), ('136', '2', '6'), ('136', '4', '7'), ('136', '4', '8'), ('136', '4', '9'),
	('137', '1', '11'), ('137', '1', '12'), ('137', '2', '13'), ('137', '2', '12'), ('137', '5', '11'), ('137', '5', '12'),
	('138', '2', '7'), ('138', '2', '8'), ('138', '2', '9'), ('138', '4', '7'), ('138', '4', '8'), ('138', '4', '9'),        
	('139', '2', '7'), ('139', '2', '8'), ('139', '4', '7'), ('139', '4', '8'),
	('140', '2', '11'), ('140', '2', '12'), ('140', '4', '11'), ('140', '4', '12'),
	('141', '3', '9'), ('141', '3', '10'), ('141', '5', '11'), ('141', '5', '12'),
	('142', '2', '1'), ('142', '2', '2'), ('142', '4', '1'), ('142', '4', '2'),
	('143', '2', '1'), ('143', '2', '2'), ('143', '4', '1'), ('143', '4', '2'),
	('144', '3', '9'), ('144', '3', '10'), ('144', '5', '11'), ('144', '5', '12'),
	('145', '2', '1'), ('145', '2', '2'), ('145', '4', '1'), ('145', '4', '2'),
	('146', '2', '1'), ('146', '2', '2'), ('146', '4', '1'), ('146', '4', '2'),
	('147', '3', '13'), ('147', '3', '12'), ('147', '4', '13'), ('147', '4', '12'),
	('148', '5', '3'), ('148', '5', '4'), ('148', '5', '5'),
	('149', '4', '13'), ('149', '4', '12'),
	('150', '4', '1'), ('150', '4', '2'),
	('151', '1', '1'), ('151', '1', '2'),
	('152', '1', '10'), ('152', '1', '11'),
	('153', '1', '10'), ('153', '1', '11'), 
	('154', '2', '10'), ('154', '2', '11');
	
	
	
	
	
	
	
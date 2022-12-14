INSERT INTO tb_tipos_disciplina (tipo_nome, tipo_valor) VALUES
	('Obrigatória', '5'),
	('Segundo Estrato', '3'),
	('Trilha', '2'),
	('Opcional', '1');
	
INSERT INTO tb_horarios (id, faixa, sigla) VALUES
	('1', '07h30-08h20', 'M1'),
	('2', '08h20-09h10', 'M2'),
	('3', '09h10-10h00', 'M3'),
	('4', '10h20-11h10', 'M4'),
	('5', '11h10-12h00', 'M5'),
	('6', '12h00-12h50', 'M6'),
	('7', '13h00-13h50', 'T1'),
	('8', '13h50-14h40', 'T2'),
	('9', '14h40-15h30', 'T3'),
	('10', '15h50-16h40', 'T4'),
	('11', '16h40-17h30', 'T5'),
	('12', '17h50-18h40', 'T6'),
	('13', '18h40-19h30', 'N1'),
	('14', '19h30-20h10', 'N2'),
	('15', '20h20-21h10', 'N3'),
	('16', '21h20-22h10', 'N4'),
	('17', '22h10-23h00', 'N5');
	
INSERT INTO tb_dias (id, identificador, dia) VALUES
	('1', '2', 'Segunda'),
	('2', '3', 'Terça'),
	('3', '4', 'Quarta'),
	('4', '5', 'Quinta'),
	('5', '6', 'Sexta');
	
INSERT INTO tb_cursos (nome, codigo, periodos) VALUES
	('Bacharelado em Sistemas de Informação', '236', '8');
	
INSERT INTO tb_disciplinas (id, nome, codigo, carga_horaria) VALUES
	('1', 'Fundamentos de Programação 1', 'CSF13', '90'),
	('2', 'Prolegômenos ao Computar', 'CSX10', '60'),
	('3', 'Tópicos Matemáticos', 'MA70E', '90'),
	('4', 'Introdução à Lógica para a Computação', 'CSD20', '45'),
	('5', 'Técnicas de Programação', 'CSE20', '60'),
	('6', 'Estrutura de Dados 1', 'CSF20', '45'),
	('7', 'Fundamentos de Sistemas de Informação', 'CSG10', '60'),
	('8', 'Geometria Analítica e Álgebra Linear', 'MA71B', '90'),
	('9', 'Teoria da Computação', 'CSA31', '45'),
	('10', 'Matemática Discreta', 'CSD21', '45'),
	('11', 'Engenharia de Software 2', 'CSE40', '45'),
	('12', 'Estrutura de Dados 2', 'CSF30', '45'),
	('13', 'Gestão da Informação em Sistemas de Informação', 'CSG30', '45'),
	('14', 'Introdução à Interação Humano-Computador', 'CSH30', '45'),
	('15', 'Sistemas Inteligentes', 'CSI30', '45'),
	('16', 'Desenvolvimento Integrado de Sistemas', 'CSM30', '45'),
	('17', 'Processamento Digital de Imagens', 'CSV30', '60'),
	('18', 'Arquitetura e Organização de Computadores', 'CSW20', '60'),
	('19', 'Sociologia', 'ES70G', '45'),
	('20', 'História da Técnica e da Tecnologia', 'ES70S', '45'),
	('21', 'Teorias da Administração', 'GE71A', '60'),
	('22', 'Comportamento Humano nas Organizações', 'GE73A', '45'),
	('23', 'Probabilidade e Estatística', 'MA70H', '60'),
	('24', 'Projeto e Análise de Algoritmos', 'CSA30', '45'),
	('25', 'Introdução a Banco de Dados', 'CSB30', '60'),
	('26', 'Análise e Projeto de Sistemas', 'CSG20', '45'),
	('27', 'Sistemas Operacionais', 'CSO30', '60'),
	('28', 'Trabalho de Integração 1', 'CSX20', '45'),
	('29', 'Teorias Organizacionais', 'GE72A', '60'),
	('30', 'Engenharia de Software', 'CSE30', '60'),
	('31', 'Redes de Computadores', 'CSR30', '45'),
	('32', 'Psicologia Aplicada ao Trabalho', 'ES70B', '30'),
	('33', 'Filosofia da Ciência e da Tecnologia', 'ES70P', '45'),
	('34', 'Sistemas Distribuídos', 'CSS30', '60'),
	('35', 'Trabalho de Integração 2', 'CSX30', '45'),
	('36', 'Trabalho de Conclusão de Curso 1', 'CSX40', '30'),
	('37', 'Trabalho de Conclusão de Curso 2', 'CSX41', '30');
	
INSERT INTO tb_itens_matriz (id, disciplina_id, periodo, pre_requisitos, curso_id, tipo_disciplina_id, peso) VALUES
	('1', '1', '1', '3', '1', '1', 0.0),
	('2', '2', '1', '2', '1', '1', 0.0),
	('3', '3', '1', '7', '1', '1', 0.0),
	('4', '4', '2', '1', '1', '1', 0.0),
	('5', '5', '2', '6', '1', '1', 0.0),
	('6', '6', '2', '3', '1', '1', 0.0),
	('7', '7', '2', '2', '1', '1', 0.0),
	('8', '8', '2', '1', '1', '1', 0.0),
	('9', '9', '3', '6', '1', '2', 0.0),
	('10', '10', '3', '4', '1', '1', 0.0),
	('11', '11', '3', '6', '1', '1', 0.0),
	('12', '12', '3', '5', '1', '1', 0.0),
	('13', '13', '3', '5', '1', '2', 0.0),
	('14', '14', '3', '7', '1', '2', 0.0),
	('15', '15', '3', '13', '1', '2', 0.0),
	('16', '16', '3', '0', '1', '2', 0.0),
	('17', '17', '3', '8', '1', '2', 0.0),
	('18', '18', '3', '6', '1', '1', 0.0),
	('19', '19', '3', '3', '1', '1', 0.0),
	('20', '20', '3', '0', '1', '2', 0.0),
	('21', '21', '3', '2', '1', '1', 0.0),
	('22', '22', '3', '0', '1', '2', 0.0),
	('23', '23', '3', '7', '1', '1', 0.0),
	('24', '24', '4', '5', '1', '1', 0.0),
	('25', '25', '4', '9', '1', '1', 0.0),
	('26', '26', '4', '5', '1', '1', 0.0),
	('27', '27', '4', '5', '1', '1', 0.0),
	('28', '28', '4', '1', '1', '1', 0.0),
	('29', '29', '4', '0', '1', '1', 0.0),
	('30', '30', '5', '2', '1', '1', 0.0),
	('31', '31', '5', '11', '1', '1', 0.0),
	('32', '32', '5', '0', '1', '1', 0.0),
	('33', '33', '5', '0', '1', '1', 0.0),
	('34', '34', '6', '0', '1', '1', 0.0),
	('35', '35', '6', '0', '1', '1', 0.0),
	('36', '36', '7', '0', '1', '1', 0.0),
	('37', '37', '8', '0', '1', '1', 0.0);
	
INSERT INTO tb_turmas (id, codigo, disciplina_id) VALUES
	('1', 'CSF13-S01', '1'), 
	('2', 'CSF13-S02', '1'), 
	('3', 'CSF13-S72', '1'), 
	('4', 'CSF13-S73', '1'), 
	('5', 'CSF13-S74', '1'),
	('6', 'CSX10-S73', '2'), 
	('7', 'CSX10-S74', '2'),
	('8', 'MA70E-S63', '3'), 
	('9', 'MA70E-S73', '3'),
	('10', 'CSD20-S71', '4'), 
	('11', 'CSD20-S72', '4'), 
	('12', 'CSD20-S73', '4'), 
	('13', 'CSE20-S01', '5'), 
	('14', 'CSE20-S71', '5'), 
	('15', 'CSE20-S72', '5'),
	('16', 'CSF20-S01', '6'), 
	('17', 'CSF20-S71', '6'), 
	('18', 'CSF20-S73', '6'),
	('19', 'CSG10-S73', '7'),
	('20', 'MA71B-S11', '8'), 
	('21', 'MA71B-S15', '8'), 
	('22', 'MA71B-S21', '8'), 
	('23', 'MA71B-S23', '8'), 
	('24', 'MA71B-S25', '8'), 
	('25', 'MA71B-S51', '8'), 
	('26', 'MA71B-S53', '8'), 
	('27', 'MA71B-S55', '8'), 
	('28', 'MA71B-S71', '8'), 
	('29', 'MA71B-S73', '8'), 
	('30', 'MA71B-S81', '8'),
	('31', 'CSA31-S71', '9'),
	('32', 'CSD21-S71', '10'), 
	('33', 'CSD21-S73', '10'),
	('34', 'CSE40-S71', '11'),
	('35', 'CSF30-S71', '12'), 
	('36', 'CSF30-S73', '12'),
	('37', 'CSG30-S73', '13'),
	('38', 'CSH30-S73', '14'),
	('39', 'CSI30-S71', '15'), 
	('40', 'CSI30-S73', '15'),
	('41', 'CSM30-S73', '16'),
	('42', 'CSV30-S73', '17'),
	('43', 'CSW20-S73', '18'),
	('44', 'ES70G-S63', '19'), 
	('45', 'ES70G-S71', '19'), 
	('46', 'ES70G-S73', '19'),
	('47', 'ES70S-S73', '20'),
	('48', 'GE71A-S73', '21'),
	('49', 'GE73A-S73', '22'),
	('50', 'MA70H-S01', '23'), 
	('51', 'MA70H-S21', '23'), 
	('52', 'MA70H-S23', '23'), 
	('53', 'MA70H-S25', '23'), 
	('54', 'MA70H-S41', '23'),
	('55', 'MA70H-S43', '23'), 
	('56', 'MA70H-S51', '23'), 
	('57', 'MA70H-S53', '23'), 
	('58', 'MA70H-S55', '23'), 
	('59', 'MA70H-S61', '23'),
	('60', 'MA70H-S71', '23'), 
	('61', 'MA70H-S73', '23'),
	('62', 'CSA30-S71', '24'), 
	('63', 'CSA30-S73', '24'),
	('64', 'CSB30-S71', '25'), 
	('65', 'CSB30-S73', '25'),
	('66', 'CSG20-S71', '26'), 
	('67', 'CSG20-S73', '26'),
	('68', 'CSO30-S71', '27'), 
	('69', 'CSO30-S73', '27'),
	('70', 'CSX20-S73', '28'),
	('71', 'GE72A-S73', '29'),
	('72', 'CSE30-S71', '30'), 
	('73', 'CSE30-S73', '30'),
	('74', 'CSR30-S71', '31'), 
	('75', 'CSR30-S73', '31'),
	('76', 'ES70B-N31', '32'), 
	('77', 'ES70B-S15', '32'), 
	('78', 'ES70B-S21', '32'), 
	('79', 'ES70B-S23', '32'), 
	('80', 'ES70B-S25', '32'),
	('81', 'ES70B-S31', '32'), 
	('82', 'ES70B-S51', '32'), 
	('83', 'ES70B-S73', '32'),
	('84', 'ES70P-S71', '33'), 
	('85', 'ES70P-S73', '33'),
	('86', 'CSS30-S71', '34'), 
	('87', 'CSS30-S73', '34'),
	('88', 'CSX30-S73', '35'),
	('89', 'CSX40-S73', '36'),
	('90', 'CSX41-E01', '37'), 
	('91', 'CSX41-ES73', '37');
		
INSERT INTO tb_horario_aula (turma_id, dia_id, horario_id) VALUES
	('1', '2', '4'), ('1', '2', '5'), ('1', '2', '6'), ('1', '3', '8'), ('1', '3', '9'), ('1', '3', '10'), 
	('2', '2', '3'), ('2', '2', '4'), ('2', '2', '5'), ('2', '5', '3'), ('2', '5', '4'), ('2', '5', '5'),
	('3', '1', '4'), ('3', '1', '5'), ('3', '1', '6'), ('3', '3', '4'), ('3', '3', '5'), ('3', '3', '6'),
	('4', '2', '7'), ('4', '2', '8'), ('4', '2', '9'), ('4', '5', '7'), ('4', '5', '8'), ('4', '5', '9'),
	('5', '2', '7'), ('5', '2', '8'), ('5', '2', '9'), ('5', '5', '7'), ('5', '5', '8'), ('5', '5', '9'),
	('6', '1', '11'), ('6', '1', '12'), ('6', '3', '11'), ('6', '3', '12'),
	('7', '1', '11'), ('7', '1', '12'), ('7', '3', '11'), ('7', '3', '12'),
	('8', '1', '1'), ('8', '1', '2'), ('8', '1', '3'), ('8', '2', '1'), ('8', '2', '2'), ('8', '2', '3'),
	('9', '1', '7'), ('9', '1', '8'), ('9', '1', '9'), ('9', '3', '7'), ('9', '3', '8'), ('9', '3', '9'),
	('10', '4', '3'), ('10', '4', '4'), ('10', '4', '5'),
	('11', '2', '10'), ('11', '2', '11'), ('11', '2', '12'),
	('12', '4', '8'), ('12', '4', '9'), ('12', '4', '10'),
	('13', '3', '2'), ('13', '3', '3'), ('13', '3', '4'), ('13', '3', '5'),
	('14', '1', '4'), ('14', '1', '5'), ('14', '2', '4'), ('14', '2', '5'),
	('15', '2', '7'), ('15', '2', '8'), ('15', '3', '7'), ('15', '3', '8'),
	('16', '2', '7'), ('16', '2', '8'), ('16', '2', '9'),
	('17', '1', '1'), ('17', '1', '2'), ('17', '1', '3'),
	('18', '1', '10'), ('18', '1', '11'), ('18', '1', '12'),
	('19', '2', '10'), ('19', '2', '11'), ('19', '5', '8'), ('19', '5', '9'),
	('20', '2', '8'), ('20', '2', '9'), ('20', '4', '8'), ('20', '4', '9'), ('20', '5', '8'), ('20', '5', '9'),
	('21', '2', '7'), ('21', '2', '8'), ('21', '3', '7'), ('21', '3', '8'), ('21', '4', '10'), ('21', '4', '11'),
	('22', '2', '10'), ('22', '2', '11'), ('22', '4', '10'), ('22', '4', '11'), ('22', '5', '10'), ('22', '5', '11'),
	('23', '1', '1'), ('23', '1', '2'), ('23', '3', '5'), ('23', '3', '6'), ('23', '5', '1'), ('23', '5', '2'),
	('24', '1', '7'), ('24', '1', '8'), ('24', '2', '3'), ('24', '2', '4'), ('24', '3', '5'), ('24', '3', '6'),
	('25', '1', '7'), ('25', '1', '8'), ('25', '2', '7'), ('25', '2', '8'), ('25', '3', '8'), ('25', '3', '9'),
	('26', '2', '1'), ('26', '2', '2'), ('26', '2', '3'), ('26', '4', '1'), ('26', '4', '2'), ('26', '4', '3'),
	('27', '1', '4'), ('27', '1', '5'), ('27', '2', '4'), ('27', '2', '5'), ('27', '3', '4'), ('27', '3', '5'),
	('28', '1', '2'), ('28', '1', '3'), ('28', '3', '2'), ('28', '3', '3'), ('28', '5', '2'), ('28', '5', '3'),
	('29', '1', '8'), ('29', '1', '9'), ('29', '4', '11'), ('29', '4', '12'), ('29', '5', '10'), ('29', '5', '11'),
	('30', '2', '9'), ('30', '2', '10'), ('30', '2', '11'), ('30', '3', '10'), ('30', '3', '11'), ('30', '3', '12'),
	('31', '4', '10'), ('31', '4', '11'), ('31', '4', '12'),
	('32', '5', '8'), ('32', '5', '9'), ('32', '5', '10'),
	('33', '1', '10'), ('33', '1', '11'), ('33', '1', '12'),
	('34', '1', '8'), ('34', '1', '9'), ('34', '1', '10'),
	('35', '4', '7'), ('35', '4', '8'), ('35', '4', '9'),
	('36', '5', '7'), ('36', '5', '8'), ('36', '5', '9'),
	('37', '4', '12'), ('37', '4', '13'), ('37', '4', '14'),
	('38', '1', '11'), ('38', '1', '12'), ('38', '1', '13'),
	('39', '4', '7'), ('39', '4', '8'), ('39', '4', '9'),
	('40', '3', '14'), ('40', '3', '15'), ('40', '3', '16'),
	('41', '5', '10'), ('41', '5', '11'), ('41', '5', '12'),
	('42', '2', '11'), ('42', '2', '12'), ('42', '4', '11'), ('42', '4', '12'),
	('43', '1', '8'), ('43', '1', '9'), ('43', '5', '10'), ('43', '5', '11'),
	('44', '1', '1'), ('44', '1', '2'), ('44', '1', '3'),
	('45', '2', '3'), ('45', '2', '4'), ('45', '2', '5'),
	('46', '4', '9'), ('46', '4', '10'), ('46', '4', '11'),
	('47', '4', '10'), ('47', '4', '11'), ('47', '4', '12'),
	('48', '2', '7'), ('48', '2', '8'), ('48', '4', '7'), ('48', '4', '8'),
	('49', '1', '12'), ('49', '1', '13'), ('49', '1', '14'),
	('51', '1', '8'), ('51', '1', '9'), ('51', '1', '10'), ('51', '1', '11'),
	('52', '2', '1'), ('52', '2', '2'), ('52', '2', '3'), ('52', '2', '4'),
	('53', '3', '5'), ('53', '3', '6'), ('53', '4', '5'), ('53', '4', '6'),
	('54', '3', '14'), ('54', '3', '15'), ('54', '3', '16'), ('54', '3', '17'),
	('55', '5', '2'), ('55', '5', '3'), ('55', '5', '4'), ('55', '5', '5'),
	('56', '4', '13'), ('56', '4', '14'), ('56', '4', '15'), ('56', '4', '16'),
	('57', '4', '8'), ('57', '4', '9'), ('57', '4', '10'), ('57', '4', '11'),
	('58', '3', '1'), ('58', '3', '2'), ('58', '3', '3'), ('58', '3', '4'),
	('59', '1', '2'), ('59', '1', '3'), ('59', '1', '4'), ('59', '1', '5'),
	('60', '3', '8'), ('60', '3', '9'), ('60', '3', '10'), ('60', '3', '11'),
	('61', '2', '9'), ('61', '2', '10'), ('61', '2', '11'), ('61', '2', '12'),
	('62', '4', '7'), ('62', '4', '8'), ('62', '4', '9'),
	('63', '3', '10'), ('63', '3', '11'), ('63', '3', '12'),
	('64', '1', '7'), ('64', '1', '8'), ('64', '5', '4'), ('64', '5', '5'),
	('65', '2', '10'), ('65', '2', '11'), ('65', '4', '10'), ('65', '4', '11'),
	('66', '3', '3'), ('66', '3', '4'), ('66', '3', '5'),
	('67', '2', '7'), ('67', '2', '8'), ('67', '2', '9'),
	('68', '1', '4'), ('68', '1', '5'), ('68', '3', '2'), ('68', '3', '3'),
	('69', '1', '10'), ('69', '1', '11'), ('69', '4', '8'), ('69', '4', '9'),
	('70', '3', '7'), ('70', '3', '8'), ('70', '3', '9'),
	('71', '1', '7'), ('71', '1', '8'), ('71', '5', '10'), ('71', '5', '11'),
	('72', '1', '8'), ('72', '1', '9'), ('72', '2', '10'), ('72', '2', '11'),
	('73', '1', '13'), ('73', '1', '14'), ('73', '1', '15'), ('73', '1', '16'),
	('74', '4', '3'), ('74', '4', '4'), ('74', '4', '5'),
	('75', '3', '10'), ('75', '3', '11'), ('75', '3', '12'),
	('76', '4', '13'), ('76', '4', '14'),
	('77', '5', '4'), ('77', '5', '5'),
	('78', '3', '14'), ('78', '3', '15'),
	('79', '5', '3'), ('79', '5', '4'),
	('80', '5', '1'), ('80', '5', '2'),
	('81', '4', '4'), ('81', '4', '5'),
	('82', '3', '10'), ('82', '3', '11'),
	('83', '2', '11'), ('83', '2', '12'),
	('84', '1', '8'), ('84', '1', '9'), ('84', '1', '10'),
	('85', '2', '8'), ('85', '2', '9'), ('85', '2', '10'),
	('86', '2', '8'), ('86', '2', '9'), ('86', '5', '8'), ('86', '5', '9'),
	('87', '3', '8'), ('87', '3', '9'), ('87', '3', '10'), ('87', '3', '11'),
	('88', '2', '10'), ('88', '2', '11'), ('88', '2', '12'),
	('89', '2', '13'), ('89', '2', '12');
	
INSERT INTO tb_disciplinas_requisitos (disciplina_id, requisito_id) VALUES
	('5', '1'),
	('6', '1'),
	('9', '24'),
	('10', '4'),
	('11', '30'),
	('12', '6'),
	('13', '7'),
	('13', '21'),
	('14', '5'),
	('14', '2'),
	('14', '21'),
	('15', '24'),
	('16', '25'),
	('16', '26'),
	('17', '12'),
	('17', '8'),
	('18', '6'),
	('22', '19'),
	('24', '10'),
	('24', '12'),
	('25', '10'),
	('25', '5'),
	('25', '12'),
	('26', '5'),
	('27', '18'),
	('28', '6'),
	('30', '26'),
	('31', '27'),
	('34', '27'),
	('34', '31'),
	('35', '28');
INSERT INTO tb_tipos_de_disciplina (tipo_nome, tipo_valor) VALUES
	('Obrigatória', '1'),
	('Segundo Estrato', '2'),
	('Trilha', '3'),
	('Opcional', '4');
	
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
	
INSERT INTO tb_cursos (nome, matriz, periodos) VALUES
	('Bacharelado em Sistemas de Informação', '236', '8');
	
INSERT INTO tb_disciplinas (id, nome, codigo, turma, periodo, pre_requisito, carga_horaria, curso_id, tipo_de_disciplina_id, peso) VALUES
	('1', 'Fundamentos de Programação 1', 'CSF13', 'CSF13-S01', '1', '3', '90', '1', '1', 0.0),
	('2', 'Fundamentos de Programação 1', 'CSF13', 'CSF13-S02', '1', '3', '90', '1', '1', 0.0),
	('3', 'Fundamentos de Programação 1', 'CSF13', 'CSF13-S72', '1', '3', '90', '1', '1', 0.0),
	('4', 'Fundamentos de Programação 1', 'CSF13', 'CSF13-S73', '1', '3', '90', '1', '1', 0.0),
	('5', 'Fundamentos de Programação 1', 'CSF13', 'CSF13-S74', '1', '3', '90', '1', '1', 0.0),
	('6', 'Prolegômenos ao Computar', 'CSX10', 'CSX10-S73', '1', '2', '60', '1', '1', 0.0),
	('7', 'Prolegômenos ao Computar', 'CSX10', 'CSX10-S74', '1', '2', '60', '1', '1', 0.0),
	('8', 'Tópicos Matemáticos', 'MA70E', 'MA70E-S63', '1', '7', '90', '1', '1', 0.0),
	('9', 'Tópicos Matemáticos', 'MA70E', 'MA70E-S73', '1', '7', '90', '1', '1', 0.0),
	('10', 'Introdução à Lógica para a Computação', 'CSD20', 'CSD20-S71', '2', '1', '45', '1', '1', 0.0),
	('11', 'Introdução à Lógica para a Computação', 'CSD20', 'CSD20-S72', '2', '1', '45', '1', '1', 0.0),
	('12', 'Introdução à Lógica para a Computação', 'CSD20', 'CSD20-S73', '2', '1', '45', '1', '1', 0.0),
	('13', 'Técnicas de Programação', 'CSE20', 'CSE20-S01', '2', '6', '60', '1', '1', 0.0),
	('14', 'Técnicas de Programação', 'CSE20', 'CSE20-S71', '2', '6', '60', '1', '1', 0.0),
	('15', 'Técnicas de Programação', 'CSE20', 'CSE20-S72', '2', '6', '60', '1', '1', 0.0),
	('16', 'Estrutura de Dados 1', 'CSF20', 'CSF20-S01', '2', '3', '45', '1', '1', 0.0),
	('17', 'Estrutura de Dados 1', 'CSF20', 'CSF20-S71', '2', '3', '45', '1', '1', 0.0),
	('18', 'Estrutura de Dados 1', 'CSF20', 'CSF20-S73', '2', '3', '45', '1', '1', 0.0),
	('19', 'Fundamentos de Sistemas de Informação', 'CSG10', 'CSG10-S73', '2', '2', '60', '1', '1', 0.0),
	('20', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S11', '2', '1', '90', '1', '1', 0.0),
	('21', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S15', '2', '1', '90', '1', '1', 0.0),
	('22', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S21', '2', '1', '90', '1', '1', 0.0),
	('23', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S23', '2', '1', '90', '1', '1', 0.0),
	('24', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S25', '2', '1', '90', '1', '1', 0.0),
	('25', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S51', '2', '1', '90', '1', '1', 0.0),
	('26', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S53', '2', '1', '90', '1', '1', 0.0),
	('27', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S55', '2', '1', '90', '1', '1', 0.0),
	('28', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S71', '2', '1', '90', '1', '1', 0.0),
	('29', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S73', '2', '1', '90', '1', '1', 0.0),
	('30', 'Geometria Analítica e Álgebra Linear', 'MA71B', 'MA71B-S81', '2', '1', '90', '1', '1', 0.0),
	('31', 'Teoria da Computação', 'CSA31', 'CSA31-S71', '3', '6', '45', '1', '2', 0.0),
	('32', 'Matemática Discreta', 'CSD21', 'CSD21-S71', '3', '4', '45', '1', '1', 0.0),
	('33', 'Matemática Discreta', 'CSD21', 'CSD21-S73', '3', '4', '45', '1', '1', 0.0),
	('34', 'Engenharia de Software 2', 'CSE40', 'CSE40-S71', '3', '6', '45', '1', '1', 0.0),
	('35', 'Estrutura de Dados 2', 'CSF30', 'CSF30-S71', '3', '5', '45', '1', '1', 0.0),
	('36', 'Estrutura de Dados 2', 'CSF30', 'CSF30-S73', '3', '5', '45', '1', '1', 0.0),
	('37', 'Gestão da Informação em Sistemas de Informação', 'CSG30', 'CSG30-S73', '3', '5', '45', '1', '2', 0.0),
	('38', 'Introdução à Interação Humano-Computador', 'CSH30', 'CSH30-S73', '3', '7', '45', '1', '2', 0.0),
	('39', 'Sistemas Inteligentes', 'CSI30', 'CSI30-S71', '3', '13', '45', '1', '2', 0.0),
	('40', 'Sistemas Inteligentes', 'CSI30', 'CSI30-S73', '3', '13', '45', '1', '2', 0.0),
	('41', 'Desenvolvimento Integrado de Sistemas', 'CSM30', 'CSM30-S73', '3', '0', '45', '1', '2', 0.0),
	('42', 'Processamento Digital de Imagens', 'CSV30', 'CSV30-S73', '3', '8', '60', '1', '2', 0.0),
	('43', 'Arquitetura e Organização de Computadores', 'CSW20', 'CSW20-S73', '3', '6', '60', '1', '1', 0.0),
	('44', 'Sociologia', 'ES70G', 'ES70G-S63', '3', '3', '45', '1', '1', 0.0),
	('45', 'Sociologia', 'ES70G', 'ES70G-S71', '3', '3', '45', '1', '1', 0.0),
	('46', 'Sociologia', 'ES70G', 'ES70G-S73', '3', '3', '45', '1', '1', 0.0),
	('47', 'História da Técnica e da Tecnologia', 'ES70S', 'ES70S-S73', '3', '0', '45', '1', '2', 0.0),
	('48', 'Teorias da Administração', 'GE71A', 'GE71A-S73', '3', '2', '60', '1', '1', 0.0),
	('49', 'Comportamento Humano nas Organizações', 'GE73A', 'GE73A-S73', '3', '0', '45', '1', '2', 0.0),
	('50', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S01', '3', '7', '60', '1', '1', 0.0),
	('51', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S21', '3', '7', '60', '1', '1', 0.0),
	('52', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S23', '3', '7', '60', '1', '1', 0.0),
	('53', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S25', '3', '7', '60', '1', '1', 0.0),
	('54', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S41', '3', '7', '60', '1', '1', 0.0),
	('55', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S43', '3', '7', '60', '1', '1', 0.0),
	('56', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S51', '3', '7', '60', '1', '1', 0.0),
	('57', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S53', '3', '7', '60', '1', '1', 0.0),
	('58', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S55', '3', '7', '60', '1', '1', 0.0),
	('59', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S61', '3', '7', '60', '1', '1', 0.0),
	('60', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S71', '3', '7', '60', '1', '1', 0.0),
	('61', 'Probabilidade e Estatística', 'MA70H', 'MA70H-S73', '3', '7', '60', '1', '1', 0.0),
	('62', 'Projeto e Análise de Algoritmos', 'CSA30', 'CSA30-S71', '4', '5', '45', '1', '1', 0.0),
	('63', 'Projeto e Análise de Algoritmos', 'CSA30', 'CSA30-S73', '4', '5', '45', '1', '1', 0.0),
	('64', 'Introdução a Banco de Dados', 'CSB30', 'CSB30-S71', '4', '9', '60', '1', '1', 0.0),
	('65', 'Introdução a Banco de Dados', 'CSB30', 'CSB30-S73', '4', '9', '60', '1', '1', 0.0),
	('66', 'Análise e Projeto de Sistemas', 'CSG20', 'CSG20-S71', '4', '5', '45', '1', '1', 0.0),
	('67', 'Análise e Projeto de Sistemas', 'CSG20', 'CSG20-S73', '4', '5', '45', '1', '1', 0.0),
	('68', 'Sistemas Operacionais', 'CSO30', 'CSO30-S71', '4', '5', '60', '1', '1', 0.0),
	('69', 'Sistemas Operacionais', 'CSO30', 'CSO30-S73', '4', '5', '60', '1', '1', 0.0),
	('70', 'Trabalho de Integração 1', 'CSX20', 'CSX20-S73', '4', '1', '45', '1', '1', 0.0),
	('71', 'Teorias Organizacionais', 'GE72A', 'GE72A-S73', '4', '0', '60', '1', '1', 0.0),
	('72', 'Engenharia de Software', 'CSE30', 'CSE30-S71', '5', '2', '60', '1', '1', 0.0),
	('73', 'Engenharia de Software', 'CSE30', 'CSE30-S73', '5', '2', '60', '1', '1', 0.0),
	('74', 'Redes de Computadores', 'CSR30', 'CSR30-S71', '5', '11', '45', '1', '1', 0.0),
	('75', 'Redes de Computadores', 'CSR30', 'CSR30-S73', '5', '11', '45', '1', '1', 0.0),
	('76', 'Psicologia Aplicada ao Trabalho', 'ES70B', 'ES70B-N31', '5', '0', '30', '1', '1', 0.0),
	('77', 'Psicologia Aplicada ao Trabalho', 'ES70B', 'ES70B-S15', '5', '0', '30', '1', '1', 0.0),
	('78', 'Psicologia Aplicada ao Trabalho', 'ES70B', 'ES70B-S21', '5', '0', '30', '1', '1', 0.0),
	('79', 'Psicologia Aplicada ao Trabalho', 'ES70B', 'ES70B-S23', '5', '0', '30', '1', '1', 0.0),
	('80', 'Psicologia Aplicada ao Trabalho', 'ES70B', 'ES70B-S25', '5', '0', '30', '1', '1', 0.0),
	('81', 'Psicologia Aplicada ao Trabalho', 'ES70B', 'ES70B-S31', '5', '0', '30', '1', '1', 0.0),
	('82', 'Psicologia Aplicada ao Trabalho', 'ES70B', 'ES70B-S51', '5', '0', '30', '1', '1', 0.0),
	('83', 'Psicologia Aplicada ao Trabalho', 'ES70B', 'ES70B-S73', '5', '0', '30', '1', '1', 0.0),
	('84', 'Filosofia da Ciência e da Tecnologia', 'ES70P', 'ES70P-S71', '5', '0', '45', '1', '1', 0.0),
	('85', 'Filosofia da Ciência e da Tecnologia', 'ES70P', 'ES70P-S73', '5', '0', '45', '1', '1', 0.0),
	('86', 'Sistemas Distribuídos', 'CSS30', 'CSS30-S71', '6', '0', '60', '1', '1', 0.0),
	('87', 'Sistemas Distribuídos', 'CSS30', 'CSS30-S73', '6', '0', '60', '1', '1', 0.0),
	('88', 'Trabalho de Integração 2', 'CSX30', 'CSX30-S73', '6', '0', '45', '1', '1', 0.0),
	('89', 'Trabalho de Conclusão de Curso 1', 'CSX40', 'CSX40-S73', '7', '0', '30', '1', '1', 0.0),
	('90', 'Trabalho de Conclusão de Curso 2', 'CSX41', 'CSX41-E01', '8', '0', '30', '1', '1', 0.0),
	('91', 'Trabalho de Conclusão de Curso 2', 'CSX41', 'CSX41-S73', '8', '0', '30', '1', '1', 0.0);
	
INSERT INTO tb_horario_aula (disciplina_id, dia_id, horario_id) VALUES
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
	('61', '2', '9'), ('61', '2', '10'), ('61', '2', '11'), ('62', '2', '12'),
	('62', '4', '7'), ('62', '4', '8'), ('62', '4', '9'),
	('63', '3', '10'), ('63', '3', '11'), ('63', '3', '12'),
	('64', '1', '7'), ('64', '1', '8'), ('64', '5', '4'), ('64', '5', '5'),
	('65', '2', '10'), ('65', '2', '11'), ('65', '4', '10'), ('65', '4', '11'),
	('66', '3', '3'), ('66', '3', '4'), ('66', '3', '5'),
	('67', '2', '7'), ('66', '2', '8'), ('66', '2', '9'),
	('68', '1', '4'), ('68', '1', '5'), ('68', '3', '2'), ('68', '3', '3'),
	('69', '1', '10'), ('69', '1', '11'), ('69', '4', '8'), ('69', '4', '9');
CREATE TABLE tb_disciplinas_equivalencias(
disciplina_id BIGINT, 
equivalencia_id BIGINT, 
PRIMARY KEY (disciplina_id, equivalencia_id),
FOREIGN KEY (disciplina_id) REFERENCES tb_disciplinas(id),
FOREIGN KEY (equivalencia_id) REFERENCES tb_disciplinas(id)
);

INSERT INTO tb_disciplinas_equivalencias (disciplina_id, equivalencia_id) VALUES
	('44', '45'),
	('49', '50'), ('49', '51'), ('49', '52'),
	('50', '51'), ('50', '52'),
	('51', '52'),
	('3', '54'), ('3', '55'),
	('54', '55'),
	('8', '61'), ('8', '91'),
	('61', '91'),
	('65', '66'),
	('20', '69'),
	('32', '80');
	
	
	
	
	
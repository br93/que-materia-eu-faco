CREATE TABLE tb_cursos (
id BIGINT AUTO_INCREMENT PRIMARY KEY, 
nome VARCHAR(255),
matriz VARCHAR(3), 
periodos INTEGER
);

CREATE TABLE tb_horarios (
id BIGINT AUTO_INCREMENT PRIMARY KEY, 
faixa VARCHAR(255), 
sigla VARCHAR(255)
);

CREATE TABLE tb_tipos_de_disciplina (
id BIGINT AUTO_INCREMENT PRIMARY KEY, 
tipo_nome VARCHAR(255), 
tipo_valor INTEGER
);

CREATE TABLE tb_alunos (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255), 
periodo INTEGER, 
registro VARCHAR(255), 
curso_id BIGINT,
FOREIGN KEY(curso_id) REFERENCES tb_cursos(id)
);

CREATE TABLE tb_disciplinas (
id BIGINT AUTO_INCREMENT PRIMARY KEY, 
nome VARCHAR(255),
codigo VARCHAR(255), 
periodo INTEGER,
carga_horaria INTEGER, 
curso_id BIGINT, 
horario_id BIGINT, 
tipo_de_disciplina_id BIGINT,
peso DOUBLE,
FOREIGN KEY(curso_id) REFERENCES tb_cursos(id),
FOREIGN KEY(horario_id) REFERENCES tb_horarios(id),
FOREIGN KEY(tipo_de_disciplina_id) REFERENCES tb_tipos_de_disciplina(id)
);

CREATE TABLE tb_alunos_disciplinas (
aluno_id BIGINT,
disciplina_id BIGINT, 
PRIMARY KEY (aluno_id, disciplina_id),
FOREIGN KEY (aluno_id) REFERENCES tb_alunos(id),
FOREIGN KEY (disciplina_id) REFERENCES tb_disciplinas(id)
);

CREATE TABLE tb_disciplinas_requisitos(
disciplina_id BIGINT, 
requisito_id BIGINT, 
PRIMARY KEY (disciplina_id, requisito_id),
FOREIGN KEY (disciplina_id) REFERENCES tb_disciplinas(id),
FOREIGN KEY (requisito_id) REFERENCES tb_disciplinas(id)
);
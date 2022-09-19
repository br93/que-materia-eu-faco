CREATE TABLE cursos_db (
id BIGINT AUTO_INCREMENT PRIMARY KEY, 
nome VARCHAR(255), 
periodos INTEGER
);

CREATE TABLE horarios_db (
id BIGINT AUTO_INCREMENT PRIMARY KEY, 
faixa VARCHAR(255), 
sigla VARCHAR(255)
);

CREATE TABLE tipos_de_disciplina_db (
id BIGINT AUTO_INCREMENT PRIMARY KEY, 
tipo_nome VARCHAR(255), 
tipo_valor INTEGER
);

CREATE TABLE alunos_db (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255), 
periodo INTEGER, 
registro VARCHAR(255), 
curso_id BIGINT,
FOREIGN KEY(curso_id) REFERENCES cursos_db(id)
);

CREATE TABLE disciplinas_db (
id BIGINT AUTO_INCREMENT PRIMARY KEY, 
nome VARCHAR(255),
codigo VARCHAR(255), 
periodo INTEGER, 
curso_id BIGINT, 
horario_id BIGINT, 
tipo_de_disciplina_id BIGINT,
FOREIGN KEY(curso_id) REFERENCES cursos_db(id),
FOREIGN KEY(horario_id) REFERENCES horarios_db(id),
FOREIGN KEY(tipo_de_disciplina_id) REFERENCES tipos_de_disciplina_db(id)
);

CREATE TABLE alunos_disciplinas_db (
aluno_id BIGINT,
disciplina_id BIGINT, 
PRIMARY KEY (aluno_id, disciplina_id),
FOREIGN KEY (aluno_id) REFERENCES alunos_db(id),
FOREIGN KEY (disciplina_id) REFERENCES disciplinas_db(id)
);

CREATE TABLE disciplinas_requisitos_db (
disciplina_id BIGINT, 
requisito_id BIGINT, 
PRIMARY KEY (disciplina_id, requisito_id),
FOREIGN KEY (disciplina_id) REFERENCES disciplinas_db(id),
FOREIGN KEY (requisito_id) REFERENCES disciplinas_db(id)
);
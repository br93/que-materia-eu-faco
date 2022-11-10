CREATE TABLE tb_perfis(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    permissao VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE tb_login(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil_id BIGINT NOT NULL,
    ativo TINYINT (1) DEFAULT 1,
    FOREIGN KEY (perfil_id) REFERENCES tb_perfis(id)
);

INSERT INTO tb_perfis(permissao) VALUES ('ADMIN');
INSERT INTO tb_perfis(permissao) VALUES ('ALUNO');
INSERT INTO tb_perfis(permissao) VALUES ('USUARIO');

INSERT INTO tb_login(usuario, senha, perfil_id) VALUES ('admin', '$2a$10$FhYhuaEKPREwyZY6LAuiF.AzveYVke0TdZySBjq8gei608ke8Snt6', '1');
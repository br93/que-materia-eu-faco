# Que Materia Eu faço API

O [Que Materia Eu Faço](http://localhost:8080/swagger-ui/) disponibiliza uma API REST que permite o cadastro de cursos, disciplinas, turmas, horarios-aula e alunos, e o acesso do aluno a seu histórico e recomendação de grades horária.

Recursos disponíveis para acesso via API:
* [**Cursos**](#cursos)
* [**Disciplinas**](#disciplinas)

## Primeiros passos
**1. Clone a aplicação**

```bash
https://github.com/br93/que-materia-eu-faco.git
```

**2. Mude o usuário e senha mysql de acordo com sua instalação MySQL**

+ abra `src/main/resources/application.properties`

+ modifique `spring.datasource.username` e `spring.datasource.password` de acordo com sua instalação do mySQL


**3. Rode o app usando [Apache Maven](https://maven.apache.org)**

```bash
mvn spring-boot:run
```

ou utilizando a IDE de sua escolha.

O app estará rodando no link <http://localhost:8080>.

## Dependências
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Apache Maven](https://maven.apache.org)

## Métodos
Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
| `GET` | Retorna informações de recurso. |
| `POST` | Cria um novo recurso. |
| `PUT` | Atualiza um recurso. |
| `PATCH` | Atualiza dados parciais de um recurso. |
| `DELETE` | Remove um recurso do sistema. |

## Recursos
+ Documentação SWAGGER: <http://localhost:8080/swagger-ui/>

## Cursos

### Buscar (/v1/cursos) [GET]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "236"
            }

+ Resposta 200 (application/json)

            {
              "codigo": "236",
              "duracao": 8,
              "nome": Bacharelado em Sistemas de Informação"
            }

### Listar (/v1/cursos/list) [GET]
+ Resposta 200 (application/json)

           {
              "paged": true,
              "pagedSize": 10,
              "pageNumber": 0,
              "content": [
                {
                  "codigo": "234",
                  "duracao": "8",
                  "nome": "Bacharelado em Sistemas de Informação"
                }
              ]
          }

### Salvar (/v1/cursos/add) [POST]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "nome": Bacharelado em Sistemas de Informação"
              "codigo": "236",
              "quantidadeDePeriodos": 8,
            }

+ Resposta 200 (application/json)

            {
              "nome": Bacharelado em Sistemas de Informação"
              "codigo": "236",
              "duracao": 8,
            }

### Deletar (/v1/cursos/delete/{codigo}) [DELETE]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "236",
            }

+ Resposta 204 (application/json)

            {}          
            
## Disciplinas

### Buscar (/v1/disciplinas) [GET]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "CSG20"
            }

+ Resposta 200 (application/json)

            {
              "nome": "Analise e Projeto de Sistemas",
              "codigo": "CSG20",
              "requisitos": [
              {
                "nome": Técnicas de Programação",
                "codigo": "CSE20",
                "requisitos": [
                  {
                    "nome": "Fundamentos de Programação 1",
                    "codigo": "CSF13",
                    "requisitos": []
                  }
                ]
            }

### Listar (/v1/disciplinas/list) [GET]
+ Resposta 200 (application/json)

           {
              "paged": true,
              "pagedSize": 10,
              "pageNumber": 0,
              "content": [
                {
                  "nome": "Fundamentos de Programação 1",
                  "codigo": "CSF13",
                  "requisitos": []
                }
                {
                  "nome": "Técnicas de Programação",
                  "codigo": "CSE20",
                  "requisitos": [
                    {
                      "nome": "Fundamentos de Programação 1",
                      "codigo": "CSF13",
                      "requisitos": []
                    }
                  ]
                }
              ]
          }

### Salvar (/v1/disciplinas/add) [POST]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "nome": "Analise e Projeto de Sistemas"
              "codigo": "CSG20",
              "cargaHoraria": 45,
            }

+ Resposta 200 (application/json)

            {
              "nome": "Analise e Projeto de Sistemas",
              "codigo": "CSG20",
              "requisitos": []"
            }

### Deletar (/v1/disciplinas/delete/{codigo}) [DELETE]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "CSG20",
            }

+ Resposta 204 (application/json)

            {}   

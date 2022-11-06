# Que Materia Eu faço API

O [Que Materia Eu Faço](http://localhost:8080/swagger-ui/) disponibiliza uma API REST que permite o cadastro de cursos, disciplinas, turmas, horarios-aula e alunos, e o acesso do aluno a seu histórico e recomendação de grades horária.

Recursos disponíveis para acesso via API:
* [**Cursos**](#cursos)
* [**Disciplinas**](#disciplinas)
* [**Turmas**](#turmas)
* [**Aulas**](#aulas)
* [**Alunos**](#alunos)
* [**Histórico**](#historico)
* [**Recomendações**](#recomendação)

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
* [Java 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
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
                  "codigo": "236",
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

### Adicionar à matriz curricular (/v1/disciplinas/register) [POST]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "curso": "236"
              "disciplina": "CSG20"
              "periodo": 4
              "preRequisitos": 5 (quantidade de materias que tem esta como pre-requisito),
              "tipoDeDisciplina": 1,
            }

+ Resposta 200 (application/json)

            {
              "curso": "Bacharelado em Sistemas de Informação"
              "disciplina": "Analise e Projeto de Sistemas",
              "periodo": 4,
              "tipoDeDisciplina": "Obrigatória"
            }


### Adicionar Equivalencia (/v1/disciplinas/add/equivalencia) [PUT]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "QB70E",
              "codigo": "QB70J"
            }

+ Resposta 200 (application/json)

            {
              "nome": "Ciências do Ambiente",
              "codigo": "QB70E",
              "requisitos": []"
            }            

### Deletar Equivalencia (/v1/disciplinas/delete/equivalencia) [PUT]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "disciplina": "QB70E",
              "equivalencia": "QB70J"
            }

+ Resposta 200 (application/json)

            {
              "nome": "Ciências do Ambiente",
              "codigo": "QB70E",
              "requisitos": []"
            }

### Adicionar Pre-Requisito (/v1/disciplinas/add/prerequisito) [PUT]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "CSG20",
              "codigo": "CSE20"
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

### Deletar Pre-Requisito (/v1/disciplinas/delete/prerequisito) [PUT]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "disciplina": "CSG20",
              "preRequisito": "CSE20"
            }

+ Resposta 200 (application/json)

            {
              "nome": "Analise e Projeto de Sistemas",
              "codigo": "CSG20",
              "requisitos": []
            }

### Deletar (/v1/disciplinas/delete/{codigo}) [DELETE]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "CSG20",
            }

+ Resposta 204 (application/json)

            {}   

## Turmas

### Buscar (/v1/turmas) [GET]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "CSG20-S73"
            }

+ Resposta 200 (application/json)

            {
              "codigo": "CSG20-S73",
              "disciplina": "Análise e Projeto de Sistemas",
              "horários": [
                {
                  "nome": Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 3,
                  "horario": "T1"
                },
                {
                  "nome": Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 3,
                  "horario": "T2"
                },
                {
                  "nome": Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 3,
                  "horario": "T3"
                }  
              ]
            }

### Listar (/v1/turmas/list) [GET]
+ Resposta 200 (application/json)

           {
              "paged": true,
              "pagedSize": 10,
              "pageNumber": 0,
              "content": [
                {
                  "codigo": "CSG20-S73",
                  "disciplina": "Análise e Projeto de Sistemas",
                  "horários": [
                    {
                  "nome": Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 3,
                  "horario": "T1"
                },
                {
                  "nome": Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 3,
                  "horario": "T2"
                },
                {
                  "nome": Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 3,
                  "horario": "T3"
                },
                {
                
                  "codigo": "CSG20-S71",
                  "disciplina": "Análise e Projeto de Sistemas",
                  "horários": [
                {
                  "nome": Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S71",
                  "dia": 4,
                  "horario": "M3"
                },
                {
                  "nome": Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 4,
                  "horario": "M4"
                },
                {
                  "nome": Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 4,
                  "horario": "M5"
                }  
              ]
              
          }

### Salvar (/v1/turmas/add) [POST]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "CSG20-S73",
              "disciplina": "CSG20",
            }

+ Resposta 200 (application/json)

            {
              "codigo": "CSG20-S73",
              "disciplina": "CSG20",
              "horarios": []"
            }

### Deletar (/v1/turmas/delete/{codigo}) [DELETE]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "CSG20-S73",
            }

+ Resposta 204 (application/json)

            {}   

## Aulas

### Buscar (/v1/aulas) [GET]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "codigo": "CSG20-S73"
            }

+ Resposta 200 (application/json)

            [
              {
                "id": 272,
                "nome": "Análise e Projeto de Sistemas",
                "codigo": "CSG20",
                "turma": "CSG20-S73",
                "dia": 3,
                "horario": "T1"
              },
              {
                "id": 273,
                "nome": "Análise e Projeto de Sistemas",
                "codigo": "CSG20",
                "turma": "CSG20-S73",
                "dia": 3,
                "horario": "T2"
              },
              {
                "id": 274,
                "nome": "Análise e Projeto de Sistemas",
                "codigo": "CSG20",
                "turma": "CSG20-S73",
                "dia": 3,
                "horario": "T4"
              }
            ]
              
### Listar (/v1/aulas/list) [GET]
+ Resposta 200 (application/json)

           {
              "paged": true,
              "pagedSize": 10,
              "pageNumber": 0,
              "content": [
                {
                  "nome": "Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 3,
                  "horario": "T1"
                },
                {
                  "nome": "Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 3,
                  "horario": "T2"
                },
                {
                  "nome": "Análise e Projeto de Sistemas",
                  "codigo": "CSG20",
                  "turma": "CSG20-S73",
                  "dia": 3,
                  "horario": "T3"
                },
              ]
              
          }

### Salvar (/v1/aulas/add) [POST]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "turma": "CSG20-S73",
              "dia": "3",
              "horario": "T1",
            }

+ Resposta 200 (application/json)

            {
              "turma": "CSG2O-S73"
              "nome": "Análise e Projeto de Sistemas"
              "codigo": "CSG20",
              "dia": "3",
              "horario": "T1"
            }

### Deletar (/v1/aulas/delete/{id}) [DELETE]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "id": 272,
            }

+ Resposta 204 (application/json)

            {}

## Historico

### Buscar Historico Completo (/v1/historico/completo) [GET]

### Buscar Historico Simples (/v1/historico/simples) [GET]

### Buscar Disciplinas Faltantes Completo (/v1/historico/faltantes/completo) [GET]

### Buscar Disciplinas Faltantes Simples (/v1/historico/faltantes/simples) [GET]

## Recomendação

### Recomendar grade horária (/v1/recomendacoes) [PATCH]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "horas": 20,
              "manha": true,
              "tarde": false,
              "noite": false
            }


### Relatório de recomendação de grade horária (/v1/recomendacoes/relatorio) [PATCH]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "horas": 20,
              "manha": true,
              "tarde": false,
              "noite": false
            }

### Recomendar disciplinas opcionais (/v1/recomendacoes/opcionais) [GET]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "dia": 2,
              "horario": T5
            }

### Relatório de disciplinas opcionais (/v1/recomendacoes/opcionais/relatorio) [GET]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "dia": 2,
              "horario": T5
            }

### Recomendar disciplinas de enriquecimento curricular (/v1/recomendacoes/enriquecimento) [GET]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "dia": 2,
              "horario": T5
            }

### Relatório de disciplinas de enriquecimento curricular (/v1/recomendacoes/enriquecimento/relatorio) [GET]

+ Requisição (application/json)

  + Parâmetros
  
            {
              "dia": 2,
              "horario": T5
            }

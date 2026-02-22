# Treina Dev - API de Tarefas (Java 21 + Spring Boot + MySQL)

Projeto MVC com API REST para gerenciamento de tarefas, persistencia em MySQL e carga inicial de dados via `banco.json`.

Aplicacao: `http://localhost:8080`

## rodar aplicação
```
.\mvnw.cmd spring-boot:run
```

## Stack usada

- Java 21
- Spring Boot 4.0.2
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- MySQL Connector/J
- Lombok
- Maven Wrapper
- Docker (multi-stage build)

## Estrutura principal

```
src/main/java/_devpro/tarefa
  |- controller
  |- service
  |- repository
  |- model
  |- dto
  |- exception
  |- config
  |- test

src/main/resources
  |- application.properties
  |- application-mysql.properties
  |- seed/banco.json
```

## Configuracao do banco (.env)

1. Copie o arquivo de exemplo:

```powershell
Copy-Item .env.example .env
```

2. Preencha o arquivo `.env` com seus dados:

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=SEU_BANCO
DB_USER=SEU_USUARIO
DB_PASSWORD=SUA_SENHA
DB_USE_SSL=false
DB_TIMEZONE=America/Sao_Paulo

JPA_DDL_AUTO=update
JPA_SHOW_SQL=true
JPA_FORMAT_SQL=true

APP_STARTUP_TESTS_ENABLED=true
```

Obs: a URL JDBC usa `createDatabaseIfNotExist=true` para criar o schema quando permitido pelo usuario do banco.

## Como rodar localmente

```powershell
cd java-vps
.\mvnw.cmd clean compile
.\mvnw.cmd spring-boot:run
```

## Endpoints criados

Base URL: `http://localhost:8080/api/tarefas`

| Metodo | Endpoint | Descricao |
|---|---|---|
| GET | `/` | Retorna a pagina estatica inicial (`index.html`) |
| GET | `/api/tarefas` | Lista todas as tarefas |
| GET | `/api/tarefas?nome=Revisao` | Busca tarefas por nome (contains, ignore case) |
| GET | `/api/tarefas/{id}` | Busca tarefa por id |
| POST | `/api/tarefas` | Cria uma nova tarefa |
| PUT | `/api/tarefas/{id}` | Atualiza uma tarefa existente |
| DELETE | `/api/tarefas/{id}` | Remove uma tarefa |

### Exemplo de payload (POST/PUT)

```json
{
  "nome": "Estudar Spring",
  "description": "Criar endpoint de tarefas",
  "isCompleted": false
}
```

### Codigos de resposta

- `200 OK`: consultas e atualizacao
- `201 Created`: criacao
- `204 No Content`: exclusao
- `400 Bad Request`: validacao de payload
- `404 Not Found`: id nao encontrado

## Seed inicial e testes de startup

- Quando a tabela esta vazia, o sistema carrega dados de `src/main/resources/seed/banco.json`.
- No startup, os testes de banco executam na ordem:

1. `conn.test`
2. `create.test`
3. `update.test`
4. `delete.test`
5. `select.test`
6. `select-by-name.test`

Para desativar os testes no startup:

```env
APP_STARTUP_TESTS_ENABLED=false
```

## Rodando os testes

```powershell
.\mvnw.cmd -q test
```

## Build com Docker

```powershell
docker build -t treina-dev-api .
docker run --env-file .env -p 8080:8080 treina-dev-api
```

# EKAN — API de Beneficiários

API REST desenvolvida em Java com Spring Boot para gerenciamento de beneficiários e seus documentos.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.1.2
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Bean Validation
- Lombok
- Springdoc OpenAPI (Swagger)
- JUnit 6
- Mockito

## Funcionalidades

- Cadastro de beneficiários com documentos.
- Listagem de beneficiários.
- Consulta de beneficiário por ID.
- Atualização dos dados do beneficiário.
- Exclusão de beneficiários.
- Consulta de documentos vinculados a um beneficiário.
- Validação dos dados de entrada.
- Tratamento global de exceções.
- Relacionamento entre beneficiários e documentos.
- Documentação interativa com Swagger.
- Testes automatizados das camadas de serviço.

## Modelagem de dados

### Beneficiário

- ID
- Nome
- Telefone
- Data de nascimento
- Data de inclusão
- Data de atualização

### Documento

- ID
- Tipo do documento
- Descrição
- Data de inclusão
- Data de atualização
- Beneficiário associado

Relacionamento: um beneficiário pode possuir vários documentos.

## Pré-requisitos

- Java 21 ou superior.
- PostgreSQL.
- Git.

## Configuração do banco de dados

Crie um banco de dados PostgreSQL chamado:

`ekan_db`

Configure as variáveis de ambiente antes de executar a aplicação:

**PowerShell:**

```powershell
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="sua_senha"
```

## Como executar o projeto

Clone o repositório:

```bash
git clone https://github.com/lisarioss/beneficiarios-api.git
```

Entre na pasta:

```bash
cd beneficiarios-api
```

Execute a aplicação no Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

A API estará disponível em:

`http://localhost:8080`

## Endpoints

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/beneficiarios` | Cadastrar beneficiário |
| GET | `/api/beneficiarios` | Listar beneficiários |
| GET | `/api/beneficiarios/{id}` | Buscar por ID |
| PUT | `/api/beneficiarios/{id}` | Atualizar beneficiário |
| DELETE | `/api/beneficiarios/{id}` | Excluir beneficiário |
| GET | `/api/beneficiarios/{beneficiarioId}/documentos` | Listar documentos |

## Documentação Swagger

Com a aplicação em execução, acesse:

`http://localhost:8080/swagger-ui/index.html`

A interface permite visualizar e executar as requisições da API.

## Executar os testes automatizados

No Windows:

```powershell
.\mvnw.cmd clean test
```

## Testes realizados

- Testes unitários dos serviços de beneficiários.
- Testes unitários dos serviços de documentos.
- Verificação de exceções para recursos inexistentes.
- Validação das operações CRUD por meio do Swagger.
- Verificação do relacionamento entre beneficiários e documentos.

# Desafio Dock: Executando a Aplicação Java

## Introdução
O Desafio Dock é uma aplicação Java que simula um cenário bancário. Nesta aplicação, você pode cadastrar portadores, abrir contas e realizar transações de saque e depósito. Para executar a aplicação, siga as instruções abaixo.

## Pré-requisitos
Antes de executar o Desafio Dock, certifique-se de ter o seguinte instalado:

1. **Java 17**: Certifique-se de ter o JDK 17 instalado em sua máquina.
2. **Docker**: Você precisará do Docker instalado para executar o banco de dados PostgreSQL.

## Instruções para Execução
1. Clone o repositório do Desafio Dock em sua máquina.
2. Navegue até a pasta `docker` no diretório do projeto.
3. Execute o seguinte comando para iniciar o banco de dados PostgreSQL usando o Docker Compose:

- `docker-compose up`

4. Crie as seguintes variáveis de ambiente (seja no computador ou na IDE):
- `DB_USER=desafiodock`
- `DB_PASSWORD=desafiodock`
  Essas variáveis contêm as credenciais para acessar o banco de dados.

## Documentação das APIs
A aplicação utiliza as seguintes tecnologias:

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **PostgreSQL**: Banco de dados relacional.
- **JPA (Java Persistence API)**: Para mapeamento objeto-relacional.
- **ModelMapper**: Biblioteca para mapeamento entre objetos.
- **JPQL (Java Persistence Query Language)**: Para consultas personalizadas.
- **Swagger**: Documentação das APIs disponível em http://localhost:8080/swagger-ui/index.html.

!Desafio Dock Logo

## Importando a Collection
Na pasta `collection`, você encontrará um arquivo JSON que pode ser importado no Insomnia ou Postman para testar as APIs.

Agora você está pronto para executar o Desafio Dock! 🚀

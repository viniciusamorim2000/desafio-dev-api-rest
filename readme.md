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
- `DB_HOST=localhost`
- `DB_PORT=5432`
- `DB_NAME=postgres`


Essas variáveis contêm as credenciais para acessar o banco de dados.

## Documentação das APIs
A aplicação utiliza as seguintes tecnologias:

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **PostgreSQL**: Banco de dados relacional.
- **JPA (Java Persistence API)**: Para mapeamento objeto-relacional.
- **ModelMapper**: Biblioteca para mapeamento entre objetos.
- **JPQL (Java Persistence Query Language)**: Para consultas personalizadas.
- **JUnit 5 e Mockito**: Para testes unitários.
- **Swagger**: Documentação das APIs disponível em http://localhost:8080/swagger-ui/index.html.

!Desafio Dock Logo

## Importando a Collection
Na pasta `collection`, você encontrará um arquivo JSON que pode ser importado no Insomnia ou Postman para testar as APIs.

# Conceitos Utilizados no Projeto

## Clean Code
O Clean Code refere-se a escrever código de maneira clara, legível e fácil de entender. É uma abordagem que enfatiza a manutenção e a qualidade do código. Alguns princípios do Clean Code incluem:

- **Responsabilidade Única (SRP):** Cada classe deve ter apenas uma responsabilidade.
- **Abstração e Encapsulamento:** Isolar detalhes de implementação e expor apenas o necessário.
- **Nomes Significativos:** Escolher nomes descritivos para variáveis, métodos e classes.
- **Comentários e Documentação:** Escrever comentários claros e documentar o código.

## SOLID
Os princípios SOLID são diretrizes para escrever código flexível, extensível e fácil de manter. Eles foram propostos por Robert C. Martin (Uncle Bob) e são amplamente aceitos na programação orientada a objetos. Aqui estão os cinco princípios SOLID:

- **Princípio da Responsabilidade Única (SRP):** Uma classe deve ter apenas uma razão para mudar.
- **Princípio Aberto-Fechado (OCP):** As entidades de software (classes, módulos, funções) devem estar abertas para extensão, mas fechadas para modificação.
- **Princípio da Substituição de Liskov (LSP):** Objetos de uma classe derivada devem ser substituíveis por objetos de sua classe base sem afetar a integridade do programa.
- **Princípio da Segregação de Interface (ISP):** Muitas interfaces específicas são melhores do que uma única interface geral.
- **Princípio da Inversão de Dependência (DIP):** Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações.

## Padrão MVC (Model-View-Controller)
O MVC é um padrão de arquitetura de software que separa uma aplicação em três componentes principais:

- **Model:** Representa os dados e a lógica de negócios da aplicação.
- **View:** Exibe os dados do Model ao usuário e recebe entradas do usuário.
- **Controller:** Gerencia a interação entre o Model e a View, processando entradas do usuário e atualizando o Model.

O uso do padrão MVC ajuda a manter a separação de preocupações e facilita a manutenção e a escalabilidade do código.

## Cloud e Banco de Dados
A aplicação utiliza o banco de dados PostgreSQL, que foi provisionado na nuvem Railway de forma gratuita. A nuvem oferece escalabilidade, disponibilidade e facilidade de gerenciamento. O Swagger da API está hospedado em https://desafio-dev-api-rest-production.up.railway.app/swagger-ui/index.html#/.

Obs: caso queria testar os endpoints da nuvem bastar alterar localhost:8080 pelo host da aplicação.

Host da aplicação: https://desafio-dev-api-rest-production.up.railway.app/

Agora você está pronto para explorar o Desafio Dock! 🚀


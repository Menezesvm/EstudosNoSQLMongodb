# EstudosNoSQLMongodb

API REST em Spring Boot construída durante os estudos de NoSQL / MongoDB com Spring Data, com foco em modelar um domínio simples de blog (usuários e posts) usando padrões orientados a documentos, em vez de um esquema relacional.

🇺🇸 [Read in English](README.md)

## Sobre o projeto

Este é um projeto de prática (baseado no curso de Spring Boot + MongoDB do Nelio Alves) explorando como a modelagem de documentos difere da modelagem relacional: documentos embutidos, DTOs para evitar loops infinitos de referência, `@DBRef` com carregamento lazy e consultas customizadas no MongoDB com regex e intervalos de data.

## Tecnologias

- Java 25
- Spring Boot 4.1.1 (Web MVC + Spring Data MongoDB)
- MongoDB
- Maven

## Modelo de domínio

- **User** — `id`, `name`, `email`, e uma lista de referências a `Post` carregada de forma lazy (`@DBRef`)
- **Post** — `id`, `date`, `title`, `body`, um `AuthorDTO` embutido, e uma lista de `CommentDTO` embutidos

Os dois DTOs (`AuthorDTO`, `CommentDTO`) são embutidos diretamente dentro dos documentos `Post` para evitar referências circulares entre `User` e `Post` e manter os dados de autor/comentário desnormalizados, como é comum em bancos orientados a documentos.

## Endpoints

**Usuários** — `/users`
| Método | Caminho | Descrição |
|---|---|---|
| GET | `/users` | Lista todos os usuários |
| GET | `/users/{id}` | Busca um usuário por id |
| POST | `/users` | Cria um usuário |
| PUT | `/users/{id}` | Atualiza um usuário |
| DELETE | `/users/{id}` | Remove um usuário |
| GET | `/users/{id}/posts` | Lista os posts de um usuário |

**Posts** — `/posts`
| Método | Caminho | Descrição |
|---|---|---|
| GET | `/posts/{id}` | Busca um post por id |
| GET | `/posts/titlesearch?text=` | Busca posts pelo título (regex, sem diferenciar maiúsculas/minúsculas) |
| GET | `/posts/fullsearch?text=&minDate=&maxDate=` | Busca posts por título, corpo ou texto de comentário dentro de um intervalo de datas |

## Destaques

- Métodos `@Query` customizados no `PostRepository` combinando filtros de regex e intervalo de datas (`fullSearch`), além do mecanismo de query derivation do Spring Data (`findByTitleContainingIgnoreCase`)
- Tratamento de exceções centralizado via `ControllerExceptionHandler` e uma `ObjectNotFoundException` customizada, seguindo o mesmo padrão de arquitetura em camadas usado no projeto [Workshop-springboot-jpa](https://github.com/Menezesvm/Workshop-springboot-jpa)
- `Instantiation` (`CommandLineRunner`) popula o banco com usuários, posts e comentários de exemplo ao iniciar a aplicação

## Executando localmente

1. Tenha uma instância do MongoDB rodando localmente (padrão: `mongodb://localhost:27017`)
2. Ajuste `src/main/resources/application.properties` se necessário
3. Execute:
   ```bash
   ./mvnw spring-boot:run
   ```

## Status

🚧 Projeto de estudo — parte de uma trilha de aprendizado mais ampla em Java/Spring Boot, na sequência dos estudos de JPA/Hibernate (relacional).

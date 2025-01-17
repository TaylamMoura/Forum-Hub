<h1 align="center"> Fórum Hub </h1>

> 🚧 Projeto finalizado 🚧

  > *Este projeto faz parte do Challenge LiterAlura do ONE (Oracle Next Education) junto com a ALura.*

## *Visão Geral*
  Fórum Hub é uma aplicação backend para um fórum de perguntas. O projeto utiliza 
Java e Spring Boot para gerenciar tópicos, autenticação e segurança.
A aplicação permite aos usuários criar, listar, atualizar e deletar tópicos, 
além de autenticar-se para acessar funcionalidades protegidas.

## Funcionalidades

A aplicação segue as operações CRUD (Create, Read, Update, Delete) para gerenciamento de tópicos no fórum:

<h3>Criar Tópico:</h3>
  
  - Os usuários podem criar novos tópicos no fórum fornecendo título, mensagem, autor e curso.
   - **Endpoint:**  POST "/topicos"
   - **Exemplo de Cadastro de Tópico**

```json
{
    "titulo": "Título do Tópico",
    "mensagem": "Conteúdo da mensagem",
    "autor": "Nome do autor",
    "curso": "Nome do curso"
}
````

  - A resposta esperada é: *201 Created*. E o retorno dos dados cadastrados, no corpo da resposta.

<h3>Listar Tópicos:</h3>

  - Todos os tópicos cadastrados podem ser listados com paginação.
  - **Endpoint:**  GET "/topicos"
  - **Exemplo de retorno que o usuário tem:**
```json
{
"id": 1,
 "titulo": "Título da dúvida",
"mensagem": "Conteúdo da mensagem",
"dataCriacao": "Data da postagem gerada automaticamente"
},
````
 - A resposta esperada é: *200 Ok*. E o retorno de todos os tópicos cadastrados, no corpo da resposta.

<h3>Listar Tópicos por id:</h3>

  - Permite buscar um tópico específico pelo seu ID.
  - **Endpoint:**  GET "/topicos/{id}"
 - **Exemplo de retorno que o usuário tem:**
```json
{
"id": 1,
"titulo": "Título da dúvida",
 "mensagem": "Conteúdo da mensagem",
"autor": "Nome do autor",
"curso": "Nome do curso",
"dataCriacao": "Data da postagem",
"status": "nao_respondido"
}
````
 - A resposta esperada é: *200 Ok*. E o retorno do tópico escolhido, no corpo da resposta.


<h3>Atualizar Tópicos:</h3>

  - Os usuários podem atualizar as informações de um tópico existente.
  - **Endpoint:**  PUT "/topicos/{ID}"
  - **Exemplo de uso:** os dados que o usuário irá enviar para atualizar.
```json
{
    "titulo": "Novo Título",
    "mensagem": "Nova Mensagem",
    "autor": "Novo Autor",
    "curso": "Novo Curso"
}
````
 - A resposta esperada é: *200 Ok*. E o retorno do tópico atualizado, no corpo da resposta.

 - **Exemplo de retorno:** Os dados que virão no corpo da resposta.
```json
{
"id": 1,
"titulo": "Título da dúvida",
 "mensagem": "Conteúdo da mensagem",
"autor": "Nome autor",
"curso": "Nome do curso",
"dataCriacao": "Data da postagem",
"status": " Status atual da pergunta"
}
````


<h3>Deletar Tópicos:</h3>

  - Os tópicos podem ser removidos do sistema.
  - **Endpoint:** DELETE /topicos/{id}
  - Usuário deve digitar o id do tópico na url
      - A resposta esperada é *204 No Content* sem retorno no corpo da resposta.

 
<h2>Autenticação</h2>

A aplicação implementa autenticação de usuários usando JWT (JSON Web Token) para garantir a segurança:

- **Login:**
    - Os usuários podem autenticar-se fornecendo login e senha, recebendo um token JWT válido.
  - **Endpoint:** POST /login
  - **Exemplo de uso:**
```json
{
    "login": "usuario",
    "senha": "senha"
}
````
 - A resposta esperada é *200 Ok* e um *Token* no corpo da resposta.

  - **Exemplo de Token retornado:** token será usado para autenticar as requisições que irá fazer.
  ```json
{
    "token":
    "yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YXkiLCJpc3MiOiJGb3"
}
````

- **Validação de Token:**
  - O token JWT é verificado para proteger as rotas da aplicação, garantindo que apenas usuários autenticados possam 
  acessar funcionalidades protegidas, tais como: Criar, atualizar e deletar tópicos.

## Dependências
O projeto utiliza as seguintes dependências:

- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Validation
- Spring Boot Starter Web
- Flyway Core e MySQL
- Spring Boot DevTools
- MySQL
- Lombok
- Spring Boot Starter Test
- Spring Security Test
- Java JWT

## Testando Endpoints
No projeto, foi utilizado a ferramento **Insomnia** para testar os endpoints. Configure as requisições para cada endpoint da forma descrita acima e visualize as respostas da sua API.
 -  O endereço usado foi: "http://localhost:8080/topicos"
.

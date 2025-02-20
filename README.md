
# NLWConnect é um Evento da RocketSeat 100% Gratuito com foco em JAVA

---

# NLW Connect

## Sobre o Projeto
O **NLW Connect** é uma API para gerenciamento de eventos, permitindo listar todos os eventos disponíveis, buscar um evento específico pelo nome e gerenciar inscrições com sistema de indicação. Este projeto está em fase inicial de desenvolvimento.

## Endpoints Disponíveis

### 📌 Enviar um novo evento
**POST** `http://localhost:8080/events`  
Envia um novo evento para o sistema.

**Exemplo de Requisição:**
```json
{
  "title": "CodeCraft1",
  "name": "codecraft1",
  "location": "Online",
  "price": 0.0,
  "startDate": "2025-03-16",
  "endDate": "2025-03-18",
  "startTime": "19:00:00",
  "endTime": "21:00:00"
}
```  

### 📌 Listar todos os eventos
**GET** `http://localhost:8080/events`  
Retorna uma lista com todos os eventos cadastrados.

### 📌 Buscar evento por nome
**GET** `http://localhost:8080/events/{name}`  
Retorna os detalhes de um evento específico pelo seu nome.

**Exemplo de Resposta:**
```json
{
    "eventId": 1,
    "title": "CodeCraft1",
    "name": "codecraft1",
    "location": "Online",
    "price": 0.0,
    "startDate": "2025-03-16",
    "endDate": "2025-03-18",
    "startTime": "19:00:00",
    "endTime": "21:00:00"
}
```  

### 📌 Inscrição em um evento
**POST** `http://localhost:8080/{name}/{idUser}`  
Inscreve um participante em um evento com a opção de indicar um amigo.

**Exemplo de Requisição:**
```json
{
    "userName": "João Silva",
    "email": "joao@email.com"
}
```

## Tecnologias Utilizadas
- Java
- Spring Boot
- Spring Data JPA
- H2 Database (ou outro banco de dados SQL)

---
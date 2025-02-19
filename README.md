# NLWConnect é um Evento da RocketSeat 100% e Gratuitamente com foco em JAVA

---

# NLW Connect

## Sobre o Projeto
O **NLW Connect** é uma API para gerenciamento de eventos, permitindo listar todos os eventos disponíveis e buscar um evento específico pelo nome. Este projeto está em fase inicial de desenvolvimento.

## Endpoints Disponíveis

### Enviar um novo evento
**POST** `http://localhost:8080/events`   
Envia um novo POST para o event

**Exemplo de Resposta:**
```json
{
  "title":"CodeCraft1",
  "location":"Online",
  "price":0.0,
  "startDate":"2025-03-16",
  "endDate":"2025-03-18",
  "startTime":"19:00:00",
  "endTime":"21:00:00"
}
```

### Listar todos os eventos
**GET** `http://localhost:8080/events`  
Retorna uma lista com todos os eventos cadastrados.

### Buscar evento por nome
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

## Tecnologias Utilizadas
- Java
- Spring Boot
- H2 Database (ou outro banco de dados, se aplicável)


1. Acesse a pasta do projeto:
   ```sh
   cd nlw-connect
   ```
2. Execute a aplicação:
   ```sh
   ./mvnw spring-boot:run
   ```
3. Acesse os endpoints via Postman ou navegador.

---